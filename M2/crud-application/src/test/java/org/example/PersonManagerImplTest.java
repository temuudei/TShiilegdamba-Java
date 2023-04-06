package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonManagerImplTest {
    private PersonManagerImpl personManager;
    private Person personOne;
    private Person personTwo;

    @BeforeEach
    void setUp() {
        personManager = new PersonManagerImpl();
        personOne = new Person("Tim", "Shiilegdamba", 25, 180000.45);
        personTwo = new Person("David", "Kim", 49, 120321.34);
        personManager.addPerson(personOne);
        personManager.addPerson(personTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addOnePersonToTheArrayList() {
        //ARRANGE
        List<Person> personList = new ArrayList<>();
        //ACT
        personList.add(personOne);
        personList.add(personTwo);
        //ASSERT
        assertEquals(personList.size(), personManager.getLength());
    }

    @Test
    void readAllPersonsInTheArrayList() {
        //ARRANGE
        String expectedFirstName = "Tim";
        String expectedFirstNameTwo = "David";
        //ASSERT
        assertEquals(personManager.getFirstName(0),expectedFirstName);
        assertEquals(personManager.getFirstName(1), expectedFirstNameTwo);
    }
}