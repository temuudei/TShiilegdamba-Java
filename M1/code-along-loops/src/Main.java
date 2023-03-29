import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Loops Demo!");
        Scanner console = new Scanner(System.in);
        String input = "";

        // while
        // scenario - process orders 1 to 5
        System.out.println("Process with while loop:");
        int orders = 1;
        while (orders <= 5) {
            System.out.println("Processing order number: " + orders);
            orders++;
        }

        // do-while
        System.out.println("Process with do-while loop:");
        orders = 5;
        do {
            System.out.println("Processing order number: " + orders);
            orders--;
        } while (orders > 0);

        // while evaluating user input - favorite hobbies
        System.out.println("Accept input until `!' entered.");
        while (input.equals("!") == false) {
            System.out.println("Favorite hobby?");
            input = console.nextLine();
            System.out.println(input + " entered.");
        }

        // for
        System.out.println("for loop - print 1 to 10");
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
        }

        System.out.println("for loop - print 10 to 1");
        for (int i = 10; i > 0 ; i--) {
            System.out.println("i = " + i);
        }

        System.out.println("Process a string in a for loop");
        String message = "Hello! How are you?";
        for (int i = 0; i < message.length(); i++) {
            System.out.println("" + message.charAt(i));
        }

        // break
        // while evaluating user input
        System.out.println("Accept input until `!' entered");
        input = "";
        while (input.equals("!") == false) {
            System.out.println("What are your favorite toppings?");
            input = console.nextLine();
            if (input.equals("anchovies") || input.equals("!")) {
                break;
            }
            System.out.println("Yum... " + input);
        }

        // continue
        // evaluate user input for valid numbers (>0)
        System.out.println("Enter your favorite whole numbers! ");
        input = "";
        while (input.equals("0") == false) {
            System.out.println("What is your favorite number?");
            input = console.nextLine();
            int nbr = Integer.parseInt(input);
            if (nbr < 0) {
                System.out.println("Oops, negative numbers are not allowed!");
                continue;
            }
            System.out.println("You have entered: " + nbr);
        }
        boolean isValid = false;
        System.out.println("Even number check");
        while (isValid == false) {
            System.out.println("Enter an even number");
            input = console.nextLine();
            int nbr = Integer.parseInt(input);
            if (nbr % 2 == 0) {
                System.out.println("Yep, that's an even number");
                isValid = true;
            }
            else {
                System.out.println("Nope, that's not an even number");
            }
        }
    }
}