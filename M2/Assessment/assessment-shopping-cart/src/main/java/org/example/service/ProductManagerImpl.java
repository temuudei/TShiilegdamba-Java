package org.example.service;

import org.example.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManagerImpl implements ProductManager {
    private Map<Integer, Product> productMap;
    private int nextID;

    public ProductManagerImpl() {
        productMap = new HashMap<>();
        nextID = 0;
    }

    @Override
    public List<Product> readAll() {
        return productMap.values().stream().toList();
    }

    @Override
    public Product deleteProduct(int index) {
        return productMap.remove(index);
    }

    @Override
    public Product createProduct(Product product) {
        nextID++;
        productMap.put(nextID, product);
        return product;
    }

    @Override
    public void updateProduct(int index, Product product) {
        productMap.put(index, product);
    }
}
