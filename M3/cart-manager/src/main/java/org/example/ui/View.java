package org.example.ui;

import org.example.models.Item;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class View {
    private TextIO io;

    public View(TextIO io) {
        this.io = io;
    }

    public int getMenuChoice ()
    {
        io.println("0. Exit");
        io.println("1. Add Item");
        io.println("2. Remove Item");
        io.println("3. View Cart");
        io.println("4. Checkout");
        return io.promptInt("Enter Choice: ", 0, 4);
    }

    public void printItems (List<Item> items)
    {
        for (Item item:items
             ) {
            io.println(item.getQty() + "x :" + item.getName());
        }
    }

    public int promptItems (List<Item> items)
    {
        return 0;
    }

    public Item promptItem ()
    {
        Item item = new Item();
        item.setName(io.prompt("Enter Item Name:"));
        item.setPrice(io.promptDouble("Enter Item Price", 1, 1000));
        item.setQty(io.promptInt("Enter Qty:", 1, 1000));
        return item;
    }

    public Item promptEditItem (Item item)
    {
        item.setQty(io.promptInt("Enter Qty:", 1, 1000));
        return item;
    }

    public void printErrors (List<String> messages)
    {
        for (String error: messages
             ) {
            io.println(error);
        }
    }

    public void print(String message) {
        io.print(message);
    }
}
