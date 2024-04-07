package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        productService.createProduct("1","A", 8);
        System.out.println(productService.getInventory("1"));
        OrderService orderService = OrderService.getInstance(productService);
        orderService.createOrder(List.of("1"),List.of(5),"1");
        System.out.println(productService.getInventory("1"));
        orderService.createOrder(List.of("1"),List.of(2),"2");
        System.out.println(productService.getInventory("1"));
//        orderService.confirmOrder("1");
        System.out.println(productService.getInventory("1"));
        orderService.cancelOrder("1");
        System.out.println(productService.getInventory("1"));
        // 8 - 5-2 -> 1 +5 -> 6
    }
}