package org.example.service;

import org.example.model.PaymentType;

import java.util.HashMap;
import java.util.Map;

public class PincodeService {
    Map<String, HashMap<String,PaymentType>> pincodes;
    public PincodeService(){
        pincodes = new HashMap<>();
    }

    public void createPincode(String sourcePinCode, String destPincode, PaymentType paymentType){
        pincodes.computeIfAbsent(sourcePinCode, f -> new HashMap<>()).put(destPincode, paymentType);
    }

    public Boolean checkPincode(String sourcePinCode, String destPincode, PaymentType paymentType){
        HashMap<String,PaymentType> destPincodes = pincodes.get(sourcePinCode);
        return destPincodes.containsKey(destPincodes) && paymentType.equals(destPincodes.get(destPincode));
    }
}
