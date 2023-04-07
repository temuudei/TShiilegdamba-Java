package org.example.bll;

import org.example.dal.PersonManager;
import org.example.models.Person;
import org.example.vierw.ConsoleIO;
import org.example.vierw.ConsoleIOImpl;

import java.util.List;

public class PersonController {
    private ConsoleIO io;

    private PersonManager personManager;

    public PersonController(ConsoleIO consoleIO, PersonManager personManager) {
        io = consoleIO;
        this.personManager = personManager;
    }

    public void run(){
        // Create a person management application that will prompt the user to:
        // 0. Exit
        // 1. Add a person
        // 2. View all people
        // 3. Update person
        // 4. Delete a person



        boolean keepRunning = true;
        while (keepRunning) {
            io.print("========== Main Menu ============");
            io.print("0. Exit\n1. Add a person\n2. View All people\n3. Update a person\n4. Delete a person");

            int choice = io.promptInt("Enter a choice: ", "Invalid Choice", 0, 4);

            switch (choice) {
                case 0: // Exit
                    keepRunning = false;
                    break;
                case 1: // Add Person
                    addPerson();
                    break;
                case 2: // View People
                    displayPeople();
                    break;
                case 3: // Update Person
                    editPerson();
                    break;
                case 4: // Delete
                    removePerson();
                    break;
            }
        }

    }
    private  void removePerson() {
        io.print("========== Remove Person =================");
        List<Person> myPersonList = personManager.readAll();
        io.printPeople(myPersonList);
        int choice = io.promptInt("Please select person: ", "Invalid choice: Please enter a number between 1-" + myPersonList.size(), 1, myPersonList.size());
        Person person = personManager.deletePerson(choice);

        io.prompt(String.format("%s has been removed\n", person.getFirstName()));
    }

    private  void editPerson() {
        System.out.println("========== Update Person =================");
        List<Person> myPersonList = personManager.readAll();
        io.printPeople(myPersonList);
        int choice = io.promptInt("Please select person: ", "Invalid choice: Please enter a number between 1-" + myPersonList.size(), 1, myPersonList.size());
        Person person = personManager.readByIndex(choice);

        String input;
        input = io.prompt(String.format("Enter First Name (%s): ", person.getFirstName()));
        if (!input.isEmpty()) {
            person.setFirstName(input);
        }
        input = io.prompt(String.format("Enter Last Name (%s): ", person.getLastName()));
        if (!input.isEmpty()) {
            person.setLastName(input);
        }

        Integer ageInput = io.promptInt(String.format("Enter Age (%d): ", person.getAge()), "", 0, 150, false);
        if (ageInput != null) {
            person.setAge(ageInput);
        }

        Double salaryInput = io.promptDouble(String.format("Enter salary (%.2f): ", person.getSalary()), "", 10000, false);
        if (salaryInput != null) {
            person.setSalary(salaryInput);
        }
        personManager.updatePerson(choice, person);
        System.out.printf("%s has been updated\n", person.getFirstName());
    }

    private  void displayPeople() {
        io.print("============= Display People ==============");
//        for (int i = 0; i < myPersonList.size(); i++) {
//            Person person = myPersonList.get(i);
//            System.out.printf("%s %s %d %f.2", person.getFirstName(), person.getLastName(), person.getAge(), person.getSalary());
//        }
        for (Person person : personManager.readAll()) {
            String output = String.format("%s %s %d $%.2f\n", person.getFirstName(), person.getLastName(), person.getAge(), person.getSalary());
            io.print(output);
        }


    }

    private  void addPerson() {
        Person person = new Person();
        io.print("=========== Add Person ==========");

        person.setFirstName(io.prompt("Enter First Name: "));
        person.setLastName(io.prompt("Enter Last Name: "));
        person.setAge(io.promptInt("Enter your age (0-150): ", "Invalid age: Please enter an age between 0-150", 0, 150));
        person.setSalary(io.promptDouble("Enter your salary (greater than 10,000): ", "Invalid salary: Please enter an greater than 10,000", 10000));

        person = personManager.createPerson(person);

        io.print(String.format("%s has been added to the list\n", person.getFirstName()));
    }
}
