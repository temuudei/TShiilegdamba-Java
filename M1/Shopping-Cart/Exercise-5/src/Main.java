import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the shopping cart app!");
        System.out.println("Are you tax-exempt? (y/n)");
        String taxExempt = sc.nextLine();

        System.out.println("Shipping? (y/n)");
        String shipping = sc.nextLine();

        System.out.println("Order quantity?");
        String orderQuantity = sc.nextLine();
        int orderQuantityInt = Integer.parseInt(orderQuantity);

        System.out.println("Promo code for free shipping?");
        String promoCode = sc.nextLine();

        System.out.println("Details:");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantityInt);
        System.out.println("Promo code: " + promoCode);
        System.out.println("Bye");
    }
}