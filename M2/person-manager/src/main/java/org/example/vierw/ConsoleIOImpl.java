package org.example.vierw;

import org.example.models.Person;

import java.util.List;
import java.util.Scanner;

public class ConsoleIOImpl implements ConsoleIO {
    private Scanner scanner;

    public ConsoleIOImpl() {
        scanner = new Scanner(System.in);
    }


    @Override
    public void print(String message){
        System.out.println(message);
    }
    @Override
    public void printPeople(List<Person> people){
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            print(String.format("%d. %s %s %d $%.2f\n", person.getId(), person.getFirstName(), person.getLastName(), person.getAge(), person.getSalary()));
        }
    }
    @Override
    public String prompt(String message){
        System.out.print(message);
        return scanner.nextLine();
    }
    @Override
    public int promptInt(String message, String errorMessage, int min, int max){
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
    @Override
    public Integer promptInt(String message, String errorMessage, int min, int max, boolean required){
        int result = -1;
        String input;
        do {
            try {
                input = prompt(message);
                if(required == false && input.isBlank()) {
                    return null;
                }
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
    @Override
    public Double promptDouble(String message, String errorMessage, double min, boolean required){
        double result = -1;
        String input;
        do {
            try {
                input = prompt(message);
                if(required == false && input.isBlank()) {
                    return null;
                }
                result = Integer.parseInt(input);
                if (result < min ) {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (result < min );
        return result;
    }
    @Override
    public double promptDouble(String message, String errorMessage, int min){
        double result = -1;
        String input;
        do {
            try {
                input = prompt(message);
                result = Double.parseDouble(input);
                if (result < min ) {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (result < min );
        return result;
    }
}
