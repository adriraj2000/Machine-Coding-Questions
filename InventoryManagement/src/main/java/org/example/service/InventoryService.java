package org.example.service;

import org.example.model.Product;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    Map<String,Product> products;
    public InventoryService(){
        products = new HashMap<>();
    }

    public void addProduct(String productId, String productName, String pincode, Integer quantity){
        products.put(productId, new Product(productId, productName,quantity,pincode));
    }

    public Boolean checkInventory(int orderedQuantity, String productId){
        synchronized (this){
            Product product = products.get(productId);
            if(orderedQuantity <= product.getQuantity()){
                product.setQuantity(product.getQuantity() - orderedQuantity);
                return true;
            }
            else return false;
        }
    }
}
