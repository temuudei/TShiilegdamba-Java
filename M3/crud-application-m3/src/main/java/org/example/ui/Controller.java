package org.example.ui;

import org.example.dal.DALException;
import org.example.domain.ComputerResult;
import org.example.domain.ComputerService;


import org.example.models.Computer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Controller {
    private ComputerService computerService;
    private View view;

    public Controller(ComputerService computerService, View view) {
        this.computerService = computerService;
        this.view = view;
    }

    public void run() {
        try {
            while (true) {
                int choice = view.getMenuChoice();
                if (choice == 0) {
                    break;
                }
                switch (choice) {
                    case 1 -> {
                        addItem();
                    }
                    case 2 -> {
                        removeComputer();
                    }
                    case 3 -> {
                        viewCart();
                    }
                    case 4 -> {
                        searchByBrandName();
                    }
                    default -> {
                        view.printErrors(new ArrayList<>());
                    }
                }
            }
        } catch (DALException ex) {
            view.print(ex.getMessage());
        }
    }

    private List<Computer> viewCart() throws DALException {
        List<Computer> items = computerService.viewComputers();
        view.printcomputers(items);
        return items;
    }

    private void addItem() throws DALException {
        Computer computer = view.promptcomputer();
        ComputerResult result = computerService.addComputer(computer);
        if (result.isSuccessful()) {
            view.print("Item has been added");
        } else {
            view.printErrors(result.getMessages());
        }
    }

    private void removeComputer() throws DALException {
        TextIO io = new ConsoleIO();
        List<Computer> computers = viewCart();
        int id = io.promptInt("Enter your computer ID to be deleted", 0, computers.size() + 1);
        computerService.removeComputer(id);
    }

    private void searchByBrandName() throws DALException {
        List<Computer> items = computerService.viewComputers();
        TextIO io = new ConsoleIO();
        String input = io.prompt("Enter your name");
        List<String> list = items.stream().filter(a -> a.getBrandName().equalsIgnoreCase(input)).map(a -> a.getBrandName()).collect(Collectors.toList());
        items.stream().filter(a -> a.getBrandName().equalsIgnoreCase(input)).forEach(a -> System.out.printf("Price: $%.2f\nCPU: %s\nGPU: %s\noperating system: %s\n", a.getPrice(), a.getCpu(), a.getGpu(), a.getOperatingSystem()));
        io.println("Number of items: " + list.size());
    }
}
