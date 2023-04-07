package org.example.model;

import java.util.Objects;

public class Product {
    private int itemID;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return itemID == product.itemID && Double.compare(product.itemPrice, itemPrice) == 0 && itemQuantity == product.itemQuantity && Objects.equals(itemName, product.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, itemName, itemPrice, itemQuantity);
    }
}
