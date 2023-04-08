package org.example.service;

import org.example.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductManager {
    List<Product> readAll();

    Product readByIndex(int index);

    Product deleteProduct(int index);

    Product createProduct(int index, Product product, int quantity);

    Map<Integer, Product> getProductMap();
}
