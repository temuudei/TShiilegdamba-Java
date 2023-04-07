package org.example.dal;

import org.example.models.Person;

import java.util.List;

public interface PersonManager {
    // Create
    Person createPerson(Person person);

    // ReadAll
    List<Person> readAll();

    // ReadByIndex
    Person readByIndex(int index);

    // Update
    void updatePerson(int index, Person person);

    // Delete
    Person deletePerson(int index);
}
