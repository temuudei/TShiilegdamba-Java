package org.example;

import org.example.models.ConsoleIO;
import org.example.models.Person;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static List<Person> myPersonList;
    private static Scanner scanner;

    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO();
        scanner = new Scanner(System.in);
        myPersonList = new ArrayList<Person>();
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n0. Exit\n1. Add a person\n2. View all people\n3. Update a person\n4. Delete a person");
            System.out.print("Enter a choice: ");
            int choice = consoleIO.promptInt("Enter a choice: ", "Invalid Choice", 0, 4);
            switch (choice) {
                case 0:
                    keepRunning = false;
                    break;
                case 1:
                    addPerson();
                    break;
                case 2:
                    displayPeople();
                    break;
                case 3:
                    editPerson();
                    break;
                case 4:
                    deletePerson();
                    break;
            }
        }
    }

    private static void deletePerson() {
        for (int i = 0; i < myPersonList.size(); i++) {
            Person person = myPersonList.get(i);
            System.out.printf("%d. %s %s %d %.2f", (i + 1), person.getFirstName(), person.getLastName(), person.getAge(), person.getSalary());
        }
        int choice = -1;
        do {
            try {
                System.out.print("Please select a person: ");
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                if (choice < 0 || choice > myPersonList.size()) {
                    System.out.println("Invalid choice: Please enter a number between 1-" + myPersonList.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (choice < 0 || choice > myPersonList.size());
        Person person = myPersonList.remove(choice - 1);
        System.out.printf("%s has been removed", person.getFirstName());
    }

    private static void editPerson() {
        for (int i = 0; i < myPersonList.size(); i++) {
            Person person = myPersonList.get(i);
            System.out.printf("%d. %s %s %d %.2f", (i + 1), person.getFirstName(), person.getLastName(), person.getAge(), person.getSalary());
        }
        int choice = -1;
        do {
            try {
                System.out.print("Please select a person: ");
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                if (choice < 0 || choice > myPersonList.size()) {
                    System.out.println("Invalid choice: Please enter a number between 1-" + myPersonList.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (choice < 0 || choice > myPersonList.size());
        Person person = myPersonList.get(choice - 1);
        String input;

        System.out.printf("Enter first name (%s)", person.getFirstName());
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            person.setFirstName(input);
        }

        System.out.printf("Enter last name (%s)", person.getLastName());
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            person.setLastName(input);
        }

        System.out.printf("Enter age (%d)", person.getAge());
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            person.setAge(Integer.parseInt(input));
        }
        person.setAge(Integer.parseInt(input));
        myPersonList.set(choice-1, person);
    }

    private static void displayPeople() {
        for (int i = 0; i < myPersonList.size(); i++) {
            Person person = myPersonList.get(i);
            System.out.printf("%s %s %d %.2f", person.getFirstName(), person.getLastName(), person.getAge(), person.getSalary());
        }
    }

    private static void addPerson() {
        Person person = new Person();
        System.out.print("Enter first name: ");
        person.setFirstName(scanner.nextLine());

        System.out.print("Enter last name: ");
        person.setLastName(scanner.nextLine());

        int age = -1;
        do {
            try {
                System.out.print("Enter your age: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age < 0 || age > 150) {
                    System.out.println("Invalid age: Please enter age between 0-150");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (age < 0 || age > 150);
        person.setAge(age);

        double salary = -1;
        do {
            try {
                System.out.print("Enter your age: ");
                salary = Integer.parseInt(scanner.nextLine());
                if (salary < 10000) {
                    System.out.println("Invalid age: Please enter age between 0-150");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Input must be a number");
            }
        } while (salary < 10000);
        person.setSalary(salary);
        myPersonList.add(person);

        System.out.println("Has been added");
    }
}