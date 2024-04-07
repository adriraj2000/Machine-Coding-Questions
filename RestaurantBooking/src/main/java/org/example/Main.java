package org.example;

import org.example.exception.BookingException;
import org.example.model.*;
import org.example.service.RestaurantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Create instance of restaurant service
        RestaurantService restaurantService = new RestaurantService();

        // Register restaurants
        Restaurant restaurant1 = new Restaurant(1, "Restaurant A", "CityA", "Area1", "CuisineA", 50, true);
        restaurantService.register(restaurant1);

        // Update availability for restaurant
        List<Availability> availability1 = new ArrayList<>();
        availability1.add(new Availability(LocalDate.of(2024, 3, 2), 12, 13)); // Availability for restaurant 1
        restaurantService.updateAvailability(1, availability1);

        // Create booking requests
        BookingRequest bookingRequest1 = new BookingRequest(2, LocalDate.of(2024, 3, 2), 12, 13);
        BookingRequest bookingRequest2 = new BookingRequest(2, LocalDate.of(2024, 3, 2), 12, 13);

        // Create ExecutorService with two threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Submit booking tasks
        executorService.submit(() -> {
            try {
                BookingResult bookingResult = restaurantService.book(1, bookingRequest1);
                System.out.println("Thread 1 - " + bookingResult.getMessage());
            } catch (BookingException e) {
                System.out.println("Thread 1 - Booking failed: " + e.getMessage());
            }
        });

        executorService.submit(() -> {
            try {
                BookingResult bookingResult = restaurantService.book(1, bookingRequest2);
                System.out.println("Thread 2 - " + bookingResult.getMessage());
            } catch (BookingException e) {
                System.out.println("Thread 2 - Booking failed: " + e.getMessage());
            }
        });

        // Shutdown executor service
        executorService.shutdown();

        // Wait for all tasks to complete
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}