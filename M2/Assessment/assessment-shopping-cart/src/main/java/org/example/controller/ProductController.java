package org.example.controller;

import org.example.model.Product;
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
        while (keepRunning) {
            io.displayMessage("Main Menu");
            io.displayMessage("1. Display Cart\n2. Remove an Item\n3. Add an Item\n4. Checkout\n5. Exit");
            int menuChoice = io.promptInt("Enter a choice ", "Invalid choice: Please only choose between (1-5)", 1, 5);
            switch (menuChoice) {
                case 1:
                    displayCart();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    addItem();
                    break;
                case 4:
                    checkOutCart();
                    break;
                case 5:
                    keepRunning = false;
                    break;
            }
        }
    }

    public Product addItemDescriptiom(int itemChoice) {
        Product product = new Product();
        switch (itemChoice) {
            case 1:
                product = addItemToProduct(1, "Hair Dryer", 25.34);
                break;
            case 2:
                product = addItemToProduct(2, "Coffee Machine", 89.23);
                break;
            case 3:
                product = addItemToProduct(3, "Water Bottle", 12.56);
                break;
            case 4:
                product = addItemToProduct(4, "Laptop", 896.67);
                break;
        }
        return product;
    }

    public Product addItemToProduct(int itemID, String itemName, double itemPrice) {
        Product product = new Product();
        product.setItemID(itemID);
        product.setItemName(itemName);
        product.setItemPrice(itemPrice);
        return product;
    }

    public void displayCart() {
        if (getMapSize() == 0) {
            io.displayMessage("Shopping cart is empty. Please add items to the cart.\n");
        }
        else {
            io.displayMessage("Your current cart items:");
            for (Product product : productManager.readAll()) {
                io.displayMessage(String.format("\nItem ID: %d\nItem name: %s\nItem price: $%.2f\nItem quantity: %d\n", product.getItemID(), product.getItemName(), product.getItemPrice(), product.getItemQuantity()));
            }
        }
    }

    public void removeItem() {
        if (getMapSize() == 0) {
            io.displayMessage("Shopping cart is empty. No items need to be removed.\n");
        } else {
            io.displayMessage("Which item would you like to remove from the cart?");
            displayCart();
            int itemChoice = io.promptInt("Enter the ID number", "Invalid choice: Please choose the correct ID number (1-4)", 1, 4);
            if (productManager.getProductMap().containsKey(itemChoice)) {
                int productQuantitySize = productManager.readByIndex(itemChoice).getItemQuantity();
                int quantity = io.promptInt("How many would you like to remove?", "Invalid item quantity: Please enter an amount greater than 0", 0);

                if (quantity == 0) {
                    io.displayMessage("No item has been removed\n");
                }
                if (productQuantitySize > 0 && quantity <= productQuantitySize) {
                    productManager.readByIndex(itemChoice).setItemQuantity(productQuantitySize - quantity);
                    if (productManager.readByIndex(itemChoice).getItemQuantity() == 0) {
                        productManager.deleteProduct(itemChoice);
                        io.displayMessage("Item has been removed\n");
                    }
                } else {
                    io.displayMessage("Please enter the right amount to be removed from the cart.\n");
                }
            } else {
                io.displayMessage("Item you entered is not in your cart.\n");
            }
        }
    }

    public void addItem() {
        io.displayMessage("Which item would you like to add to the cart?");
        io.displayMessage("\n1. Hair Dryer - $25.34\n2. Coffee Machine - $89.23\n3. Water Bottle - $12.56\n4. Laptop - $896.57");
        int itemChoice = io.promptInt("Enter a choice", "Invalid choice: Please only choose between (1-4)", 1, 4);
        int itemQuantity = io.promptInt("How many would you like to get?", "Invalid item quantity: Please enter an amount greater than 0", 0);
        Product product = addItemDescriptiom(itemChoice);
        productManager.createProduct(itemChoice, product, itemQuantity);
        io.displayMessage(String.format("%s has been added to the cart\n", product.getItemName()));
    }

    public void checkOutCart() {
        if (getMapSize() == 0) {
            io.displayMessage("Shopping cart is empty. Please add items to the cart before checking out.\n");
        }
        else {
            double totalPrice = 0;
            for (Product product : productManager.readAll()) {
                totalPrice += product.getItemQuantity() * product.getItemPrice();
            }
            io.displayMessage(String.format("Your total price is: %.2f\nGoodbye!\n", totalPrice));
            productManager.getProductMap().clear();
        }
    }

    public int getMapSize() {
        return productManager.readAll().size();
    }
}
