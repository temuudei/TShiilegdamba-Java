package org.example.service;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinInMemImpl implements Bin {
    private List<Product> products = new ArrayList<>();
    public int getLength() {
        return products.size();
    }

    public String getCurrentProductName() {
        return products.get(0).getName();
    }

    public Product vend() {
        return products.remove(0);
    }

    public void loadOne(Product product) {
        products.add(product);
    }

    public void loadAll(Collection<Product> products) {
        this.products.addAll(products);
    }

    public int dump() {
        int temp = products.size();
        products.clear();
        return temp;
    }
}
