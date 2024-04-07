package org.example.strategy;

import org.example.model.Ride;
import org.example.model.RideStatus;

import java.util.List;
import java.util.stream.Collectors;

public class PreferredVehicleStrategy implements RideSelectionStrategy{

    public Ride findRide(List<Ride> rides, String origin, String destination, String preferredVehicle, int requiredSeats) {
        return rides.stream().filter(ride -> ride.getRideStatus().equals(RideStatus.PROGRESS) &&
                ride.getVehicle().getName().equals(preferredVehicle) &&
                ride.getOrigin().equals(origin) &&
                ride.getDestination().equals(destination) &&
                ride.getVehicle().getAvailableSeats() >= requiredSeats).collect(Collectors.toList()).get(0);
    }
}
