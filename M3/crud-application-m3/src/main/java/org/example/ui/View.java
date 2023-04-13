package org.example.ui;

import org.example.models.Computer;
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
        io.println("1. Add computer");
        io.println("2. Remove computer");
        io.println("3. View Cart");
        io.println("4. Search by a brand name");
        io.println("5. Checkout");
        return io.promptInt("Enter Choice: ", 0, 5);
    }

    public void printcomputers (List<Computer> computers)
    {
        for (Computer computer:computers
        ) {
            io.print("Computer ID: " + computer.getId());
            io.print("\nBrand name: " + computer.getBrandName());
            io.print("\nCPU: " + computer.getCpu());
            io.print("\nGPU: " + computer.getGpu());
            io.print("\nOperating system: " + computer.getOperatingSystem());
            io.print("\nPrice: $" + computer.getPrice() + "\n\n");
        }
    }

    public int promptcomputers (List<Computer> computers)
    {
        return 0;
    }

    public Computer promptcomputer ()
    {
        Computer computer = new Computer();
        computer.setBrandName(io.prompt("Enter computer Name:"));
        computer.setPrice(io.promptDouble("Enter computer Price", 1, 100000000));
        computer.setReleaseYear(io.promptInt("Enter relate date:", 1990, 2023));
        computer.setGpu(io.prompt("Enter GPU:"));
        computer.setCpu(io.prompt("Enter CPU"));
        computer.setOperatingSystem(io.prompt("Enter operating system:"));
        return computer;
    }

    public Computer promptEditcomputer (Computer computer)
    {
        computer.setReleaseYear(io.promptInt("Enter release year:", 1990, 20023));
        return computer;
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
