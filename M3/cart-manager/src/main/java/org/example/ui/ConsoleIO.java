package org.example.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleIO implements TextIO {
    Scanner scanner;

    public ConsoleIO() {
        scanner = new Scanner(System.in);
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
                if(result< min || result > max){
                    System.out.println("Invalid number, must be between " + min + " and " + max);
                }
            }catch (Exception ex){
                System.out.println("Invalid input, must be a number");
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
                if(result< min || result > max){
                    System.out.println("Invalid number, must be between " + min + " and " + max);
                }
            }catch (Exception ex){
                System.out.println("Invalid input, must be a number");
            }
        }while(result < min || result > max);
        return result;
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }
}
