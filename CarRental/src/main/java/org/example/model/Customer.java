package org.example.model;


import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private int location;
    private List<Vehicle> vehicleBooked;

    public Customer(String customerId, int location) {
        this.customerId = customerId;
        this.location = location;
        this.vehicleBooked = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<Vehicle> getVehicleBooked() {
        return vehicleBooked;
    }

    public void setVehicleBooked(List<Vehicle> vehicleBooked) {
        this.vehicleBooked = vehicleBooked;
    }
}
