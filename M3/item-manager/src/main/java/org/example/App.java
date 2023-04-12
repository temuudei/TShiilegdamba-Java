package org.example;

import org.example.view.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class App {
    public static void main(String[] args) {
//        ItemRepository repo = new ItemFIleRepositoryImpl();
//        ItemService service = new ItemService(repo);
//        TextIO consoleIO = new ConsoleIO();
//        View view = new View(consoleIO);
//        Controller controller = new Controller(view,service);
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}