package org.example.dal;

import org.example.models.Person;

import java.util.HashMap;
import java.util.List;

public class PersonManagerHashImpl implements PersonManager{
    private HashMap<Integer, Person> map;
    private int nextId;
    public PersonManagerHashImpl() {
        map = new HashMap<>();
        nextId = 0;
    }

    @Override
    public Person createPerson(Person person) {
        nextId++;

        person.setId(nextId);
        map.put(person.getId(), person);
        return person;
    }

    @Override
    public List<Person> readAll() {
        return map.values().stream().toList();
    }

    @Override
    public Person readByIndex(int index) {
        return map.get(index);
    }

    @Override
    public void updatePerson(int index, Person person) {
        map.put(index, person);
    }

    @Override
    public Person deletePerson(int index) {
        return map.remove(index);
    }
}
