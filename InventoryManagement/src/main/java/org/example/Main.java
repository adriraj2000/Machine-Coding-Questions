package org.example;

import org.example.model.Buyer;
import org.example.model.PaymentType;
import org.example.model.Product;
import org.example.service.BuyerService;
import org.example.service.InventoryService;
import org.example.service.OrderService;
import org.example.service.PincodeService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BuyerService buyerService = new BuyerService();
        InventoryService inventoryService = new InventoryService();
        PincodeService pincodeService = new PincodeService();
        OrderService orderService = new OrderService(pincodeService);

        pincodeService.createPincode("110096","110096",PaymentType.COD);
        pincodeService.createPincode("110094","110094",PaymentType.COD);

        Buyer buyer1 = new Buyer("adriraj","110096");
        Buyer buyer2 = new Buyer("rahul","110094");

        buyerService.addBuyer("adriraj","110096");
        buyerService.addBuyer("rahul","110094");
        System.out.println(buyerService.getBuyer("adriraj"));

        Product product1 = new Product("p1","parleg",3,"110096");
        Product product2 = new Product("p2","iphone",4,"110096");
        Product product3 = new Product("p3","macbook",1,"110094");
        Product product4 = new Product("p4","table",2,"110094");

        inventoryService.addProduct("p1","parleg","110096",3);
        inventoryService.addProduct("p2","iphone","110096",4);
        inventoryService.addProduct("p3","macbook","110094",1);
        inventoryService.addProduct("p4","table","110094",2);

        orderService.addOrder("o1",buyer1, PaymentType.COD, List.of(product1,product3));
        orderService.addOrder("o2",buyer2, PaymentType.COD, List.of(product2,product4));

        System.out.println(orderService.getOrder("o1"));
    }
}