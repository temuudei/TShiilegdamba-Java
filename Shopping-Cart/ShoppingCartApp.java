package org.example.shoppingcart;// Please start by renaming this file to ShoppingCartApp.java

public class ShoppingCartApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");
        // Product:
        int productID = 1; // Product ID 1
        int productCategory = 2; // Product Cost is 2.56
        float productCost = 2.56f; //
        float productPrice = 4.99f;
        int productQuantity = 78;

        //1. Assign variables to each of the elements above.

        //2. Write code to calculate the total cost of the product based on the inventory.
        float totalCost = productQuantity * productCost;
        System.out.println("Total Cost: " + totalCost);
        //3. Write code to calculate the profit margin of the product.
        float profitMargin = productPrice - productCost;
        System.out.println("Profit Margin: " + profitMargin);
        //4. Write code to calculate the total potential profit.
        // total profit? profitMargin * productQuantity
        float totalProfit = profitMargin * productQuantity;
        System.out.println("Total Profit: " + totalProfit);
        System.out.println("Bye");
    }
}
