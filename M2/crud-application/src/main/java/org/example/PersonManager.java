package org.example;

import java.util.function.Supplier;

public interface PersonManager {
    void addPerson(Person person);
    void readAll();
    int getLength();
    String getFirstName(int index);
}
