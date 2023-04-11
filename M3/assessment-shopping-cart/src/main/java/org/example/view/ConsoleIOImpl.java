package org.example.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleIOImpl implements ConsoleIO {
    private Scanner scanner;

    public ConsoleIOImpl() {
        scanner = new Scanner(System.in);
    }

    //Displays message
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    //Asks for user input
    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    //Gets int type input from user with minimum and maximum
    @Override
    public int promptInt(String message, String errorMessage, int min, int max) {
        int result = -1;
        String input;
        do {
            try {
                input = prompt(message);
                result = Integer.parseInt(input);
                if (result < min || result > max) {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (result < min || result > max);
        return result;
    }

    //Gets int type input from with only minimum
    public int promptInt(String message, String errorMessage, int min) {
        int result = -1;
        String input;
        do {
            try {
                input = prompt(message);
                result = Integer.parseInt(input);
                if (result < min) {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (result < min);
        return result;
    }
}
