package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private OrderState orderState;
    public List<Integer> getQuantityOrdered() {
        return quantityOrdered;
    }

    private List<Integer> quantityOrdered;

    public void addQuantity(int quantity){
        quantityOrdered.add(quantity);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    private List<Product> productList;

    public Order(String orderId, OrderState orderState, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.createdAt = createdAt;
        productList = new ArrayList<>();
        quantityOrdered = new ArrayList<>();
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private LocalDateTime createdAt;
}
