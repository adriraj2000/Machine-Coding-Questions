package org.example.service;

import org.example.model.Buyer;
import org.example.model.Order;
import org.example.model.PaymentType;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    PincodeService pincodeService;
    Map<String, Order> orders;
    public OrderService(PincodeService pincodeService){
        orders = new HashMap<>();
        this.pincodeService = pincodeService;
    }

    public Order getOrder(String orderId){
        return orders.get(orderId);
    }

    public void addOrder(String orderId, Buyer buyer, PaymentType paymentType, List<Product> products){
        List<Product> validProducts = new ArrayList<>();
        for(Product product : products){
            if(pincodeService.checkPincode(buyer.getPincode(), product.getPincode(), paymentType)){
                validProducts.add(product);
            }
        }
        Order order = new Order(orderId, buyer, paymentType,validProducts);
        orders.put(orderId, order);
    }
}
