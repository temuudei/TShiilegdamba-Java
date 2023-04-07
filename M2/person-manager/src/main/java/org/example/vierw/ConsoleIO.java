package org.example.vierw;

import org.example.models.Person;

import java.util.List;

public interface ConsoleIO {
    void print(String message);

    void printPeople(List<Person> people);

    String prompt(String message);

    int promptInt(String message, String errorMessage, int min, int max);

    Integer promptInt(String message, String errorMessage, int min, int max, boolean required);

    Double promptDouble(String message, String errorMessage, double min, boolean required);

    double promptDouble(String message, String errorMessage, int min);
}
