package org.example.data;

import org.example.models.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactJdbcRepositoryTest {
    ContactJdbcRepository repository;
    @BeforeEach
    void setUp() {
        repository = new ContactJdbcRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldFindAll() {
        List<Contact> contacts = repository.findAll();
        System.out.println(contacts);
        assertNotNull(contacts);
        assertEquals(2, contacts.size());
    }

    @Test
    void shouldFindByName() {
        Contact contact = repository.findByName("Temuudei", "Shiilegdamba");
        assertEquals("Temuudei", contact.getFirstName());
    }


}