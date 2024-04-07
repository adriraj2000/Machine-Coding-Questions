package org.example.service;

import org.example.model.Customer;
import org.example.model.Station;
import org.example.model.Vehicle;

import java.util.*;

public class CarRentalService {
    private static CarRentalService instance;
    Map<String, Station> stations;
    Map<Station, List<Vehicle>> stationVehicleMap;
    List<Customer> customers;

    private CarRentalService(){
        stations = new HashMap<>();
        stationVehicleMap = new HashMap<>();
        customers = new ArrayList<>();
    }

    public void onboardStation(String stationId, int location, List<Vehicle> vehicles){
        Station station = new Station(stationId,location);
        stations.put(stationId, station);
        stationVehicleMap.put(station, vehicles);
    }

    public void addCustomer(String customerId, int location){
        Customer customer = new Customer(customerId, location);
        customers.add(customer);
    }

    public List<Station> searchVehicles(Vehicle vehicle){
        PriorityQueue<Station> topStations = new PriorityQueue<>((a,b)-> (int)
                (a.getVehicleRates().
                        get(vehicle.getVehicleType()) - b.getVehicleRates().get(vehicle.getVehicleType())));
        for(Station station : stationVehicleMap.keySet()){
            if(stationVehicleMap.get(station).contains(vehicle)){
                topStations.offer(station);
            }
        }

        List<Station> searchResult = new ArrayList<>();
        while (!topStations.isEmpty()){
            searchResult.add(topStations.poll());
        }
        return searchResult;
    }

    public void bookVehicle(Customer customer, Vehicle vehicle){
        //
    }

    public static CarRentalService getInstance() {
        if(instance == null){
            instance = new CarRentalService();
        }
        return instance;
    }
}
