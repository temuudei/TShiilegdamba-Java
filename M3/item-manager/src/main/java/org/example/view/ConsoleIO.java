package org.example.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleIO implements TextIO {
    private Scanner scanner;

    public ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public int promptInt(String message, int min, int max) {
        int result = 0;
        do {
            String input = prompt(message);
            try{
                result = Integer.parseInt(input);
                if(result < min || result > max){
                    System.out.println("Number must be greater than " + min + " and less than " + max);
                }
            }catch (NumberFormatException ex){
                System.out.println("Invalid number");
            }
        }while(result < min || result > max);

        return result;
    }

    @Override
    public double promptDouble(String message, double min, double max) {
        double result = 0;
        do {
            String input = prompt(message);
            try{
                result = Double.parseDouble(input);
                if(result < min || result > max){
                    System.out.println("Number must be greater than " + min + " and less than " + max);
                }
            }catch (NumberFormatException ex){
                System.out.println("Invalid number");
            }
        }while(result < min || result > max);

        return result;

    }

    @Override
    public boolean promptBoolean(String message, String answer) {
        return prompt(message).equals(answer);
    }
}
