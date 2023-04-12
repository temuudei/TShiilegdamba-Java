package org.example.ui;

public interface TextIO {
    String prompt (String message);
    int promptInt (String message, int min, int max);
    double promptDouble (String message, double min, double max);
    void println (String message);
    void print (String message);
}
