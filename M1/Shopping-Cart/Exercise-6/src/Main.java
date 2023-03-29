import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float price = 4.99f;
        System.out.println("Welcome to the shopping cart app!");
        System.out.println("Are you tax-exempt? (y/n)");
        String taxExempt = sc.nextLine();
        float tax = 0.0f;

        System.out.println("Shipping? (y/n)");
        String shipping = sc.nextLine();
        float shippingCost = 0.0f;
        String promoCode = "";
        if (shipping.equalsIgnoreCase("y")) {
            System.out.println("Please choose the option below:");
            System.out.println("1. Standard shipping: press 1");
            System.out.println("2. Two-day shipping: press 2");
            System.out.println("3. Overnight shipping: press 3");
            String shippingOption = sc.nextLine();

            if (shippingOption.equalsIgnoreCase("1")) {
                System.out.println("Promo code for free shipping?");
                promoCode = sc.nextLine();
                if (!promoCode.isEmpty()) {
                    shippingCost = 0.0f;
                }
                else {
                    shippingCost = 2.00f;
                }
            }
            else if (shippingOption.equalsIgnoreCase("2")) {
                shippingCost = 5.00f;
            }
            else {
                shippingCost = 10.00f;
            }
        }
        System.out.println("Order quantity?");
        String orderQuantity = sc.nextLine();
        int orderQuantityInt = Integer.parseInt(orderQuantity);
        float total = orderQuantityInt * price;
        float totalTemp = 0.0f;

        if (taxExempt.equalsIgnoreCase("y")) {
            if (total >= 100 && total < 500) {
                total = total - (total * 0.05f);
            }
            else {
                total = total - (total * 0.10f);
            }
        }
        else if (taxExempt.equalsIgnoreCase("n")) {
            if (total >= 100 && total < 500) {
                totalTemp = total - (total * 0.05f);
                tax = totalTemp * 0.07f;
                total = totalTemp + (tax);
            }
            else {
                totalTemp = total - (total * 0.10f);
                tax = totalTemp * 0.07f;
                total = totalTemp + (tax);
            }
        }

        System.out.println("Promo code for free shipping?");

        System.out.println("Details:");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantityInt);
        System.out.println("Tax: " + tax);
        System.out.println("Promo code: " + promoCode);
        System.out.println("Shipping cost: " + shippingCost);
        System.out.println("Total price: $" + String.format("%.2f", total + shippingCost));
        System.out.println("Bye");
    }
}