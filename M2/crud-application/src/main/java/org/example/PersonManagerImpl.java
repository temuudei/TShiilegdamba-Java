package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PersonManagerImpl implements PersonManager {
    private List<Person> personList = new ArrayList<>();
    @Override
    public void addPerson(Person person) {
        personList.add(person);
    }

    @Override
    public void readAll() {
        for (Person p : personList) {
            System.out.printf("\nFirst name: %s\nLast name: %s\nAge: %d\nSalary: %.2f\n",
                    p.getfName(), p.getlName(), p.getAge(), p.getSalary());
        }
    }

    @Override
    public int getLength() {
        return personList.size();
    }

    @Override
    public String getFirstName(int index) {
        return personList.get(index).getfName();
    }
}
