package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface ProductManager {
    List<Product> readAll();

    Product deleteProduct(int index);

    Product createProduct(Product product);

    void updateProduct(int index, Product product);
}
