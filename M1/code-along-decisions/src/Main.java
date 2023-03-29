import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Decisions App!");
        Scanner console = new Scanner(System.in);

        // Snooze the alarm when you are too tired (if-else)
        System.out.println("Wake up");
        boolean tooTired = true;
        if (tooTired == true) {
            System.out.println("Too tired and snooze");
        } else {
            System.out.println("Let's get up and get at it");
        }

        // Decide to stop at a stop light (else-if)
        System.out.println("Approaching stop light!");
        System.out.println("Stop light color? ");
        String lightColor = console.nextLine();

        if ("RED".equalsIgnoreCase(lightColor)) {
            System.out.println("Stop");
        }
        else if ("YELLOW".equalsIgnoreCase(lightColor)) {
            System.out.println("Slow down and stop");
        }
        else if ("GREEN".equalsIgnoreCase(lightColor)) {
            System.out.println("Go");
        }
        else {
            System.out.println("Invalid color");
        }
        // Decide to speed through a yellow stoplight (nested-if)
        System.out.println("Approaching stop light changing color!");
        System.out.println("What is the stop light color: ");
        lightColor = console.nextLine();

        if ("RED".equalsIgnoreCase(lightColor)) {
            System.out.println("Stop");
        }
        else if ("YELLOW".equalsIgnoreCase(lightColor)) {
            System.out.println("How far from the light");
            String input = console.nextLine();
            int distance = Integer.parseInt(input);
            if (distance >= 20) {
                System.out.println("Slow down and stop");
            }
            else {
                System.out.println("Speed up");
            }
        }
        else if ("GREEN".equalsIgnoreCase(lightColor)) {
            System.out.println("Go");
        }

        // Decide which direction your adventure will go in? (switch)
        System.out.println("Which way do you want to go?");
        System.out.println("N - North");
        System.out.println("S - South");
        System.out.println("E - East");
        System.out.println("W - West");
        System.out.println("======");
        System.out.print("Which direction?");
        String direction = console.nextLine();
        switch (direction) {
            case "N": {
                System.out.println("Go North");
                break;
            }
            case "S": {
                System.out.println("Go South");
                break;
            }
            case "E": {
                System.out.println("Go East");
                break;
            }
            case "W": {
                System.out.println("Go West");
                break;
            }
            default: {
                System.out.println("Invalid input");
                break;
            }
        }
    }
}