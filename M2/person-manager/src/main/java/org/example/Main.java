package org.example;


import org.example.bll.PersonController;
import org.example.dal.PersonManager;
import org.example.dal.PersonManagerHashImpl;
import org.example.vierw.ConsoleIO;
import org.example.vierw.ConsoleIOImpl;

public class Main {

    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIOImpl();
        PersonManager manager = new PersonManagerHashImpl();
        PersonController controller = new PersonController(io, manager);
        controller.run();
    }
}