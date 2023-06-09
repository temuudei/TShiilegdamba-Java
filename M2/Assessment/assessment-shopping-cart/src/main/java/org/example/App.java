package org.example;

import org.example.controller.ProductController;
import org.example.service.ProductManager;
import org.example.service.ProductManagerImpl;
import org.example.view.ConsoleIO;
import org.example.view.ConsoleIOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //Initiating the objects
        ConsoleIO io = new ConsoleIOImpl();
        ProductManager productManager = new ProductManagerImpl();
        ProductController productController = new ProductController(io, productManager);
        productController.run();
    }
}