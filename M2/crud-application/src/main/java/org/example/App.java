package org.example;

import java.util.Scanner;

public class App {
    static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        PersonManagerImpl personManager = new PersonManagerImpl();
        Person person = new Person();
        int userInputMenuChoice = 0;

        while (true) {
            System.out.println("\n1. Add a person\n2. See all the people in the directory\n3. Quit");
            userInputMenuChoice = Integer.parseInt(console.nextLine());
            if (userInputMenuChoice == 1) {
                person.setfName(promptUserForString("Enter first name:"));
                person.setlName(promptUserForString("Enter last name:"));
                person.setAge(Integer.parseInt(promptUserForString("Enter age:")));
                person.setSalary(Double.parseDouble(promptUserForString("Enter salary:")));
                personManager.addPerson(person);
                person = new Person();
            }
            else if (userInputMenuChoice == 2) {
                personManager.readAll();
            }
            else {
                break;
            }
        }
    }
    private static String promptUserForString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }
}