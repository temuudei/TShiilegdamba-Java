package org.example.dal;

import org.example.models.Contact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactJdbcTemplateRepositoryTest {
    ContactJdbcTemplateRepository repository;

    public ContactJdbcTemplateRepositoryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        repository = context.getBean(ContactJdbcTemplateRepository.class);
    }

    @BeforeAll
    static void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.update("call set_known_good_state();");
    }
    @Test
    void shouldfindAll() {
        List<Contact> contacts = repository.findAll();
        System.out.println(contacts);
        assertNotNull(contacts);
        assertEquals(3, contacts.size());
    }

    @Test
    void shouldfindById() {
        Contact expected = new Contact();
        expected.setContactId(2);
        expected.setFirstName("Garret");
        expected.setLastName("Paradis");
        expected.setEmail("gparandis@gmail.com");
        expected.setPhone("(123)4567");

        Contact actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Contact contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jordan");
        contact.setEmail("mjordan@gmail.com");
        contact.setPhone("911");

        Contact actual = repository.add(contact);
        System.out.println(actual);
        assertNotNull(actual);
        assertTrue(actual.getContactId() > 0);
    }

    @Test
    void shouldUpdate() {
        Contact contact = new Contact();
        contact.setContactId(1);
        contact.setFirstName("Temuudei");
        contact.setLastName("Shiilegdamba");
        contact.setEmail("Shiilegdamba");
        contact.setPhone("(123)4567");

        assertTrue(repository.update(contact));
        assertEquals(contact, repository.findById(1));
    }

    @Test
    void shouldNotUpdateMissing() {
        Contact contact = new Contact();
        contact.setContactId(1212121);
        contact.setFirstName("Temuudei");
        contact.setLastName("Shiilegdamba");
        contact.setEmail("Shiilegdamba");
        contact.setPhone("(123)4567");

        assertFalse(repository.update(contact));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(101010100));
    }
}