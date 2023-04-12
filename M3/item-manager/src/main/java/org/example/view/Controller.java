package org.example.view;

import org.example.domain.ItemResult;
import org.example.domain.ItemService;
import org.example.models.Item;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    private View view;
    private ItemService service;

    public Controller(View view, ItemService service) {
        this.view = view;
        this.service = service;
    }

    public void run(){
        while(true){
            int choice = view.printMenu();
            if(choice == 0){
                break;
            }
            switch (choice){
                case 1 -> {
                    addItem();
                }
            }
        }


    }

    private void addItem() {
        Item item = view.getNewItem();
        ItemResult result = service.addItem(item);
        if(result.isSuccessful()){
            view.display("Item has been added");

        }else{
            view.displayErrors(result.getMessages());
        }
    }
}
