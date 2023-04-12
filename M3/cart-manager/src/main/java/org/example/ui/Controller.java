package org.example.ui;

import org.example.dal.DALException;
import org.example.domain.ItemResult;
import org.example.domain.ItemService;
import org.example.models.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Controller {
    private ItemService itemService;
    private View view;

    public Controller(ItemService itemService, View view) {
        this.itemService = itemService;
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

    private void viewCart() throws DALException {
        List<Item> items = itemService.viewCart();
        view.printItems(items);

    }

    private void addItem() throws DALException {
        Item item = view.promptItem();
        ItemResult result = itemService.addItem(item);
        if (result.isSuccessful()) {
            view.print("Item has been added");
        } else {
            view.printErrors(result.getMessages());
        }
    }
}
