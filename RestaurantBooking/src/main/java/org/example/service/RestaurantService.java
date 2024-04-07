package org.example.service;

import org.example.exception.BookingException;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService implements BookableEntityService<Restaurant> {
    private List<Restaurant> restaurants = new ArrayList<>();

    @Override
    public void register(Restaurant entity) {
        synchronized (restaurants) {
            restaurants.add(entity);
        }
    }

    @Override
    public void updateAvailability(int entityId, List<Availability> availability) {
        Restaurant restaurant = findRestaurantById(entityId);
        if (restaurant != null) {
            restaurant.setAvailability(availability);
        }
    }

    @Override
    public List<Restaurant> search(SearchCriteria criteria) {
        List<Restaurant> results = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (matchCriteria(restaurant, criteria)) {
                results.add(restaurant);
            }
        }
        return results;
    }

    @Override
    public BookingResult book(int entityId, BookingRequest bookingRequest) throws BookingException {
        Restaurant restaurant = findRestaurantById(entityId);
        if (restaurant == null) {
            throw new BookingException("Restaurant not found");
        }

        synchronized (restaurant) {
            // Check availability
            for (Availability availability : restaurant.getAvailability()) {
                if (availability.getDate().equals(bookingRequest.getBookingDate())
                        && availability.getStartTime() <= bookingRequest.getStartTime()
                        && availability.getEndTime() >= bookingRequest.getEndTime()) {
                    // Table available, book it
                    restaurant.removeAvailability(availability);
                    return new BookingResult(true, "Table booked successfully");
                }
            }

            // Table not available
            return new BookingResult(false, "Table not available for the requested time slot");
        }
    }

    private Restaurant findRestaurantById(int id) {
        synchronized (restaurants) {
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getId() == id) {
                    return restaurant;
                }
            }
        }
        return null;
    }

    private boolean matchCriteria(Restaurant restaurant, SearchCriteria criteria) {
        return (criteria.getCity() == null || restaurant.getCity().equalsIgnoreCase(criteria.getCity()))
                && (criteria.getArea() == null || restaurant.getArea().equalsIgnoreCase(criteria.getArea()))
                && (criteria.getCuisine() == null || restaurant.getCuisine().equalsIgnoreCase(criteria.getCuisine()))
                && (criteria.getCostForTwo() == 0 || restaurant.getCostForTwo() <= criteria.getCostForTwo())
                && (!criteria.isVeg() || restaurant.isVeg());
    }
}
