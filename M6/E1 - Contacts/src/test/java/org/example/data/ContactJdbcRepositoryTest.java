package org.example.data;

import org.example.models.Contact;
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

    @Test
    void shouldFindAll() {
        List<Contact> contacts = repository.findAll();
        assertNotNull(contacts);
        assertEquals(2, contacts.size());
    }

    @Test
    void shouldFindByName() {
        Contact contact = repository.findByName("Garrett", "Paradis");
        assertEquals("Garrett", contact.getFirstName());

        contact = repository.findByName("Temuudei", "Shiilegdamba");
        assertEquals("Temuudei", contact.getFirstName());
    }

    @Test
    void shouldAdd() {
        Contact contact = new Contact();
        contact.setFirstName("Randall");
        contact.setLastName("Clapper");
        contact.setEmail("theteach@gmail.com");
        contact.setPhone("(111) 111-1112)");
        Contact actual = repository.add(contact);
        assertNotNull(actual);
        assertEquals(contact, actual);
    }

    @Test
    void shouldUpdate() {
        Contact contact = new Contact();
        contact.setContactId(1);
        contact.setFirstName("Not Garrett");
        contact.setLastName("Not Paradis");
        contact.setEmail("Not Email");
        contact.setPhone("Not Phone");
        assertTrue(repository.update(contact));
        assertEquals(contact, repository.findById(1));
    }

    @Test
    void shouldNotUpdate() {
        Contact contact = new Contact();
        contact.setContactId(10000);
        contact.setFirstName("Not Garrett");
        contact.setLastName("Not Paradis");
        contact.setEmail("Not Email");
        contact.setPhone("Not Phone");
        assertTrue(repository.update(contact));
        assertEquals(contact, repository.findById(1));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(3));
    }

    @Test
    void shouldNotDelete() {
        assertFalse(repository.delete(30000));
    }
}