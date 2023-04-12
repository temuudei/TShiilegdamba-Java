package org.example.view;

import org.example.models.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class View {

    TextIO io;

    public View(TextIO io) {
        this.io = io;
    }

    public int printMenu() {
        io.print("0. Exit");
        io.print("1. Add");
        int choice = io.promptInt("Enter Choice: ", 0, 1);
        return choice;
    }

    public Item getNewItem() {
        Item item = new Item();
        item.setName(io.prompt("Enter item name"));
        return item;
    }

    public void display(String message) {
        io.print(message);
    }

    public void displayErrors(List<String> messages) {
        for (String message: messages) {
            io.print(message);
        }
    }
}
