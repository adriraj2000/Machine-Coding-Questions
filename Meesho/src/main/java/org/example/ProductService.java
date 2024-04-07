package org.example;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private Map<String,Product> productMap;
    public ProductService(){
        productMap = new HashMap<>();
    }

    public Product getProduct(String productId){
        return productMap.get(productId);
    }

    public void createProduct(String productId, String name, Integer count){
        Product product = new Product(productId, name, count);
        productMap.put(productId, product);
    }

    public int getInventory(String productId){
        Product product = productMap.get(productId);
        if(product == null){
            System.out.println("Given product does not exists");
            return -1;
        }
        return product.getCount();
    }
}
