package org.example;
public class ShoppingCart {
    public double checkoutShoppingCart (Item[] items, double taxRate, double discountCode) {
        double subtotal = calculateSubtotal(items);
        subtotal = includeDiscountInSubtotal(discountCode, subtotal);
        double tax = calculateTax(taxRate, subtotal);
        double total = subtotal + tax;
        return total;
    }
    private double calculateTax(double taxRate, double subtotal) {
        double tax = subtotal * taxRate;
        return tax;
    }
    private double includeDiscountInSubtotal(double discountCode, double subtotal) {
        double discount = subtotal * discountCode;
        subtotal -= discount;
        return subtotal;
    }
    private double calculateSubtotal(Item[] items) {
        double subtotal = 0.00;
        for (int i = 0; i < items.length; i++) {
            subtotal += items[i].getPrice();
        }
        return subtotal;
    }
}