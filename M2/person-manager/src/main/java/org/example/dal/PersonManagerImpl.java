package org.example.dal;

import org.example.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonManagerImpl implements PersonManager {
    private List<Person> myPersonList;

    public PersonManagerImpl() {
        myPersonList = new ArrayList<>();
    }

    // Create
    @Override
    public Person createPerson(Person person){
        myPersonList.add(person);
        return person;
    }
    // ReadAll
    @Override
    public List<Person> readAll(){
        return myPersonList;
    }
    // ReadByIndex
    @Override
    public Person readByIndex(int index){
        return myPersonList.get(index);
    }
    // Update
    @Override
    public void updatePerson(int index, Person person){
        myPersonList.set(index, person);
    }
    // Delete
    @Override
    public Person deletePerson(int index){
        return myPersonList.remove(index);
    }

}
