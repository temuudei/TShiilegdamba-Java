package org.example.view;

public interface ConsoleIO {
    void displayMessage(String message);

    String prompt(String message);

    int promptInt(String message, String errorMessage, int min, int max);

    int promptInt(String message, String errorMessage, int min);
}
