package org.example.view;

import java.util.Scanner;

public class ConsolIOImpl implements ConsolIO {
    private Scanner scanner;

    public ConsolIOImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

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
}
