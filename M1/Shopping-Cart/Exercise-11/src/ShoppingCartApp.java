import java.util.Scanner;

/**
 * Refactor the code to use the following methods:
 * 1. Display a list of choices from an array. Returns nothing. Needs an array
 * of choices (strings).
 * 2. Prompt the user for strings. Returns a string. Needs a string to prompt
 * the user with.
 * 3. Prompt the user for an integer. Returns an integer. Needs a string to
 * prompt the user with.
 *
 * Note: We will not create method(s) for calculating the total cost until we
 * learn more about Object-Oriented Programming and some additional data
 * structures.
 */

public class ShoppingCartApp {

    public static void main(String[] args) {
        java.util.Scanner console = new java.util.Scanner(System.in);
        System.out.println("Welcome to the shopping cart app!");
        String[] addresses = { "123 Main St", "456 Main St", "789 Main St" };
        String[] sizes = { "small", "medium", "large" };

        boolean confirm = false;
        String taxExempt = "";
        String shipping = "";
        String promoCode = "";
        String[] validTaxExemptResponses = new String[]{"y", "n"};
        String[] validShippingMethods = new String[]{"standard", "overnight", "twoday"};

        while (!confirm) {
            int addressIndex = 0;
            int sizeIndex = 0;
            // Prompt for tax exempt
            taxExempt = promptUserForString("Are you tax-exempt? (y/n)", validTaxExemptResponses);

            // Prompt for shipping address
            displayChoices(addresses);
            int address = promptUserForInt("Shipping address?");

            // Prompt for shipping
            shipping = promptUserForString("Shipping? (standard/overnight/twoday)", validShippingMethods);

            // Prompt for order quantity
            int orderQuantity = promptUserForInt("Order quantity?");

            // Prompt for Size
            displayChoices(sizes);
            sizeIndex = promptUserForInt("Size?");


            // Prompt for promo code
            System.out.println("Promo code for free shipping?");
            promoCode = console.nextLine();

            // Print details
            System.out.println("\nDetails:");
            System.out.println("Tax-exempt: " + taxExempt);
            System.out.println("Address: " + addresses[address - 1]);
            System.out.println("Shipping: " + shipping);
            System.out.println("Size: " + sizes[sizeIndex - 1]);
            System.out.println("Order quantity: " + orderQuantity);
            System.out.println("Promo code: " + promoCode);
            System.out.println("Confirm Order y/n");
            confirm = "y".equals(console.nextLine());
        }
        System.out.println("Bye");
    }

    public static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i+1) + ". " + choices[i]);
        }
    }

    public static String promptUserForString(String prompt) {
        java.util.Scanner console = new java.util.Scanner(System.in);
        System.out.println(prompt);
        return console.nextLine();
    }

    public static String promptUserForString(String prompt, String[] values) {
        boolean isValid = false;
        String input = "";
        do {
            input = promptUserForString(prompt);
            for (int i = 0; i < values.length; i++) {
                if (input.equalsIgnoreCase(values[i])) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("Hey, invalid entry. Please try again.");
            }
        } while (!isValid);
        return input;
    }
    public static int promptUserForInt(String prompt) {
        java.util.Scanner console = new java.util.Scanner(System.in);
        boolean isValid = false;
        int result = -1;

        while (!isValid) {
            System.out.println(prompt);
            try {
                result = Integer.parseInt(console.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
        return result;
    }
}
