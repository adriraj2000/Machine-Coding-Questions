package org.example.model;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private Buyer buyer;
    private List<Product> products;
    private PaymentType paymentType;

    public Order(String orderId, Buyer buyer, PaymentType paymentType, List<Product> products){
        this.orderId = orderId;
        this.buyer = buyer;
        this.products = products;
        this.paymentType = paymentType;
    }
}
