package org.example.strategy;

import org.example.model.Ride;
import org.example.model.RideStatus;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MostVacantStrategy implements RideSelectionStrategy{
    public Ride findRide(List<Ride> rides, String origin, String destination, String preferredVehicle, int requiredSeats) {
        List<Ride> possibleRides = rides.stream().filter(ride -> ride.getRideStatus().equals(RideStatus.PROGRESS) &&
                ride.getOrigin().equals(origin) &&
                ride.getDestination().equals(destination)).collect(Collectors.toList());
        Collections.sort(possibleRides, Comparator.comparing(r -> r.getVehicle().getAvailableSeats()));
        return (possibleRides.size() > 0) ? possibleRides.get(possibleRides.size()-1) : null;
    }
}
