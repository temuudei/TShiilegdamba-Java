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

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    @Override
    public List<Product> readAll() {
        return productMap.values().stream().toList();
    }

    @Override
    public Product readByIndex(int index) {
        return productMap.get(index);
    }

    @Override
    public Product deleteProduct(int index) {
        return productMap.remove(index);
    }

    @Override
    public Product createProduct(int index, Product product, int quantity) {
        if (productMap.containsKey(index)) {
            product.setItemQuantity(readByIndex(index).getItemQuantity() + quantity);
            productMap.put(index, product);
        }
        else {
            product.setItemQuantity(quantity);
            productMap.put(index, product);
        }
        return product;
    }
}
