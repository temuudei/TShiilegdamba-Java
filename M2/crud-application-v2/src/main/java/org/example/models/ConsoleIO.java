package org.example.models;

import java.util.Scanner;

public class ConsoleIO {
    private Scanner scanner;
    public ConsoleIO() {
        scanner = new Scanner(System.in);
    }
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

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
        } while (result < max || result > max);
        return result;
    }

    public void print(String message) {
        System.out.println(message);
    }
    public double promptDouble(String message, String errorMessage, int min) {
        double result = -1;
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
