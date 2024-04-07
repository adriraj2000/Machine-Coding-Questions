package org.example.service;

import org.example.model.Buyer;

import java.util.HashMap;
import java.util.Map;

public class BuyerService {
    Map<String, Buyer> buyers;
    public BuyerService(){
        buyers = new HashMap<>();
    }

    public Buyer getBuyer(String buyerId){
        return buyers.get(buyerId);
    }

    public void addBuyer(String buyerId, String pincode){
        buyers.put(buyerId, new Buyer(buyerId,pincode));
    }
}
