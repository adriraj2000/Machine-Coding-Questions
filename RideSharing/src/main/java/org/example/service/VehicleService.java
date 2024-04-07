package org.example.service;

import org.example.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleService {
    private Map<String, Vehicle> vehicleMap;
    private UserService userService;
    public VehicleService(UserService userService){
        this.userService = userService;
        vehicleMap = new HashMap<>();
    }

    public void addVehicle(String owner, String name, String vehicleNumber, int availableSeats){
        if(userService.getUser(owner) == null){
            System.out.println("Please register first");
            return;
        }
        if(vehicleMap.containsKey(vehicleNumber)){
            System.out.println("Vehicle " + vehicleNumber + " already exists");
            return;
        }
        Vehicle vehicle = new Vehicle(owner, name, vehicleNumber, availableSeats);
        vehicleMap.put(vehicleNumber, vehicle);
    }

    public Vehicle getVehicle(String vehicleNumber){
        return vehicleMap.get(vehicleNumber);
    }
}
