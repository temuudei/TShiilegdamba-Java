package org.example;

import org.example.dal.ItemListRepository;
import org.example.dal.ItemRepository;
import org.example.domain.ItemService;
import org.example.domain.ItemServiceImpl;
import org.example.ui.ConsoleIO;
import org.example.ui.Controller;
import org.example.ui.TextIO;
import org.example.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class Main {
    public static void main(String[] args) {
//        ItemRepository itemRepository = new ItemListRepository();
//        ItemService itemService = new ItemServiceImpl(itemRepository);
//        TextIO io = new ConsoleIO();
//        View view = new View(io);
//        Controller controller = new Controller( itemService, view);
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}