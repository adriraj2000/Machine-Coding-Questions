package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentGateway {

    Map<Integer, Client> clients = new HashMap<>();
    public void onboardClient(Client client){
        clients.put(clientName);
    }

    public void removeClient(String clientName){
        clients.remove(clientName);
    }

    public boolean hasClient(String clientName){
        return clients.contains(clientName);
    }


}
