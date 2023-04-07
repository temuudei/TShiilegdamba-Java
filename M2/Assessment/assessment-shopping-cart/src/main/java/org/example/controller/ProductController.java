package org.example.controller;

import org.example.service.ProductManager;
import org.example.view.ConsoleIO;

public class ProductController {
    private ConsoleIO io;
    private ProductManager productManager;

    public ProductController(ConsoleIO io, ProductManager productManager) {
        this.io = io;
        this.productManager = productManager;
    }

    public void run() {
        boolean keepRunning = true;
        io.displayMessage("Main Menu");
    }
}
