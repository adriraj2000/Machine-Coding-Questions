package org.example.strategy;

import org.example.model.Ride;

import java.util.List;

public interface RideSelectionStrategy {
    Ride findRide(List<Ride> rides, String origin, String destination, String preferredVehicle, int requiredSeats);
}
