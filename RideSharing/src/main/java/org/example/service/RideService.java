package org.example.service;

import org.example.model.Ride;
import org.example.model.RideStatus;
import org.example.model.Vehicle;
import org.example.strategy.PreferredVehicleStrategy;
import org.example.strategy.RideSelectionStrategy;
import org.example.strategy.RideStrategyFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RideService {
    private Map<String, Ride> rideMap;
    private VehicleService vehicleService;
    public RideService(VehicleService vehicleService){
        this.vehicleService = vehicleService;
        rideMap = new HashMap<>();
    }

    public void offerRide(Vehicle vehicle, String origin, String destination){
        if(rideMap.containsKey(vehicle.getOwner()) &&
                rideMap.get(vehicle.getOwner()).getRideStatus().equals(RideStatus.PROGRESS)){
            System.out.println("Ride in progress");
            return;
        }
        Ride ride = new Ride(vehicle, origin, destination);
        rideMap.put(vehicle.getOwner(), ride);
    }

    public List<Ride> getAllRides(){
        return rideMap.values().stream().collect(Collectors.toList());
    }

    public Ride selectRide(String userName, String origin, String destination, int seatsRequired,
                           String rideSelectionType, String preferredVehicle){
        RideSelectionStrategy rideSelectionStrategy = RideStrategyFactory.getRideSelection(rideSelectionType);
        Ride selectedRide = rideSelectionStrategy.findRide(getAllRides(), origin, destination, preferredVehicle, seatsRequired);
        int remainingSeats = selectedRide.getVehicle().getAvailableSeats() - seatsRequired;
        selectedRide.getVehicle().setAvailableSeats(remainingSeats);
        rideMap.put(selectedRide.getVehicle().getOwner(), selectedRide);
        return selectedRide;
    }

    public void endRide(String vehicleOwner) {
        rideMap.remove(vehicleOwner);
    }
}
