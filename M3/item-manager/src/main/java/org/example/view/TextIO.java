package org.example.view;

public interface TextIO {
    void print(String message);
    String prompt(String message);
    int promptInt(String messsage, int min, int max);
    double promptDouble(String message, double min, double max);
    boolean promptBoolean(String message, String answer);
}
