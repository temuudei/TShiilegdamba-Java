package org.example.service;

import org.example.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManagerImpl implements ProductManager {
    private Map<Integer, Product> productMap;

    public ProductManagerImpl() {
        productMap = new HashMap<>();
    }

    //Returns the current hashmap
    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    //Returns all values of the hashmap
    @Override
    public List<Product> readAll() {
        return productMap.values().stream().toList();
    }

    //Returns the value of the hashmap at the specified index
    @Override
    public Product readByIndex(int index) {
        return productMap.get(index);
    }

    //Returns the key and value pair that have been removed from the hashmap
    @Override
    public Product deleteProduct(int index) {
        return productMap.remove(index);
    }

    //Adds the new Product object to the hashmap and returns it
    @Override
    public Product createProduct(int index, Product product, int quantity) {
        if (productMap.containsKey(index)) {
            product.setItemQuantity(readByIndex(index).getItemQuantity() + quantity);
        } else {
            product.setItemQuantity(quantity);
        }
        productMap.put(index, product);
        return product;
    }
}
