package org.example.model;


import java.util.HashMap;
import java.util.Map;

public class Station {
    private String stationId;
    private int location;
    private Map<VehicleType, Double> vehicleRates;

    public Map<VehicleType, Double> getVehicleRates() {
        return vehicleRates;
    }

    public void setVehicleRates(Map<VehicleType, Double> vehicleRates) {
        this.vehicleRates = vehicleRates;
    }
    public Station(String stationId, int location) {
        this.stationId = stationId;
        this.location = location;
        vehicleRates = new HashMap<>();
        vehicleRates.put(VehicleType.SUV, 12.0);
        vehicleRates.put(VehicleType.HATCHBACK, 11.0);
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
