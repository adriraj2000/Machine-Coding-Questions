package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private static OrderService instance;
    private ProductService productService;
    private Map<String, Order> orderMap;
    private OrderService(ProductService productService){
        this.productService = productService;
        orderMap = new HashMap<>();
    }

    private boolean isValidItem(String productId,Integer quantityOrdered){
        return productService.getInventory(productId) >= quantityOrdered;
    }

    public void createOrder(List<String> productIds, List<Integer> quantityOrdered, String orderId){
        int totalProducts = productIds.size();
        Order order = new Order(orderId, OrderState.PROCESSING, LocalDateTime.now());
        for(int i = 0;i<totalProducts;i++){
            if(isValidItem(productIds.get(i), quantityOrdered.get(i))){
                Product product = productService.getProduct(productIds.get(i));
                order.addQuantity(quantityOrdered.get(i));
                int productCount = product.getCount();
                product.setCount(productCount - quantityOrdered.get(i));
                order.addProduct(product);
            }
            else{
                System.out.println("Either the product does not exists or not sufficient quantity");
            }
        }
        orderMap.put(orderId, order);
    }

    public void confirmOrder(String orderId){
       Order order = orderMap.get(orderId);
       order.setOrderState(OrderState.CONFIRMED);
       orderMap.put(orderId, order);
    }

    private void restoreOrder(String orderId){
        Order currentOrder = orderMap.get(orderId);
        List<Product> products = currentOrder.getProductList();
        List<Product> restoredProducts = new ArrayList<>();
        for(int i = 0;i<products.size();i++){
            Product product = products.get(i);
            int quantityOrdered = currentOrder.getQuantityOrdered().get(i);
            product.setCount(product.getCount() + quantityOrdered);
            restoredProducts.add(product);
        }
        currentOrder.setProductList(restoredProducts);
        orderMap.put(orderId, currentOrder);
    }

    public void cancelOrder(String orderId){
        if(orderMap.get(orderId).getOrderState().equals(OrderState.PROCESSING) ){
            restoreOrder(orderId);
        }
    }

    public static OrderService getInstance(ProductService productService) {
        if(instance == null){
            instance = new OrderService(productService);
        }
        return instance;
    }
}
