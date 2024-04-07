package org.example.model;

import lombok.Data;

@Data
public class Ride {
    private Vehicle vehicle;
    private String origin;
    private String destination;
    private RideStatus rideStatus;
    public Ride(Vehicle vehicle, String origin, String destination){
        this.vehicle = vehicle;
        this.origin = origin;
        this.destination = destination;
        rideStatus = RideStatus.PROGRESS;
    }
}
