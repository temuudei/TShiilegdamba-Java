package org.example.ui;

import org.example.dal.DALException;
import org.example.domain.ComputerResult;
import org.example.domain.ComputerService;


import org.example.models.Computer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        List<Computer> computers = viewCart();
        ComputerResult result = computerService.removeComputer(computers.get(0).getId());
    }
}
