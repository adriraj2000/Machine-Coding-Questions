package org.example.strategy;

public class RideStrategyFactory {
    public static RideSelectionStrategy getRideSelection(String rideSelectionType){
        if(rideSelectionType.equals("Preferred")){
            return new PreferredVehicleStrategy();
        }
        else if(rideSelectionType.equals("Most Vacant")){
            return new MostVacantStrategy();
        }
        return null;
    }
}
