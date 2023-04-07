package org.example;

import org.example.controller.ProductController;
import org.example.service.ProductManager;
import org.example.service.ProductManagerImpl;
import org.example.view.ConsoleIO;
import org.example.view.ConsoleIOImpl;

public class App {
    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIOImpl();
        ProductManager productManager = new ProductManagerImpl();
        ProductController productController = new ProductController(io, productManager);
        productController.run();
    }
}