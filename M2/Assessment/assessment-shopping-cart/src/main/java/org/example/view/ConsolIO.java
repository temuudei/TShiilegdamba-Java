package org.example.view;

public interface ConsolIO {
    void displayMessage(String message);

    String prompt(String message);

    int promptInt(String message, String errorMessage, int min, int max);
}
