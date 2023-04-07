package org.example.service;

import org.example.model.Printer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrinterManager {
    private Map<String, Printer> printerMap;
    public PrinterManager() {
        printerMap = new HashMap<>();
    }

    public void createrPrinter(String nameID, Printer printer) {
        printerMap.put(nameID, printer);
    }

    public Printer readAllPrinters() {
        //return printerMap;
    }
}
