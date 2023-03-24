import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner console = new java.util.Scanner(System.in);

        System.out.println("Welcome to the shopping cart app!");

        // Create arrays to contain addresses and sizes
        String[] addresses = {"123 Main St", "456 Main st", "789 Main St"};
        for (int i = 0; i < addresses.length; i++) {
            System.out.println((i+1) + ". " + addresses[i]);
        }
        // Prompt for shipping address
        int shippingAns = 0;
        String shippingInput = "";
        do {
            System.out.println("Shipping address?");
            shippingInput = console.nextLine();
            shippingAns = Integer.parseInt(shippingInput);
        } while (shippingAns < 1 || shippingAns > 3);


        System.out.println("Order quantity?");
        String orderQuantity = console.nextLine();

        // Prompt for Size
        String[] size = {"small", "medium", "large"};
        for (int i = 0; i < size.length; i++) {
            System.out.println((i+1) + ". " + size[i]);
        }

        String sizeInput = "";
        int sizeInt = 0;
        do {
            System.out.println("Size: ");
            sizeInput = console.nextLine();
            sizeInt = Integer.parseInt(sizeInput);
        } while (sizeInt < 1 || sizeInt > 3);


        System.out.println("Promo code for shipping?");
        String promoCode = console.nextLine();

        // Print details
        System.out.println("\nDetails:");
        if (shippingInput.equalsIgnoreCase("1")) {
            System.out.println("Shipping address: " + addresses[0]);
        }
        else if (shippingInput.equalsIgnoreCase("2")) {
            System.out.println("Shipping address: " + addresses[1]);
        }
        else {
            System.out.println("Shipping address: " + addresses[2]);
        }

        System.out.println("Order quantity: " + orderQuantity);

        if (sizeInput.equalsIgnoreCase("1")) {
            System.out.println("Size: " + size[0]);
        }
        else if (sizeInput.equalsIgnoreCase("2")) {
            System.out.println("Size: " + size[1]);
        }
        else {
            System.out.println("Size: " + size[2]);
        }
        System.out.println("Promo code: " + promoCode);
        System.out.println("Bye");
    }
}