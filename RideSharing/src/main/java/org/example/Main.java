package org.example;

import org.example.model.Gender;
import org.example.service.RideService;
import org.example.service.UserService;
import org.example.service.VehicleService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.addUser("Rohan", Gender.M, 23);
        userService.addUser("Sameer", Gender.M, 23);
        userService.addUser("Rohan", Gender.M, 23);
        userService.addUser("Neha", Gender.F, 24);
        userService.addUser("Rekha", Gender.F, 21);
        userService.addUser("Nisha", Gender.F, 25);
        VehicleService vehicleService = new VehicleService(userService);
        vehicleService.addVehicle("Rohan","Activa","KA-01",2);
        vehicleService.addVehicle("Sameer","Baleno","KA-02",5);
        RideService rideService = new RideService(vehicleService);
        rideService.offerRide(vehicleService.getVehicle("KA-01"),"Bangalore","Mysore");
    }
}