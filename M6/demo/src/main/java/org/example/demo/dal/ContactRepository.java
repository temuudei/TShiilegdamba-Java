package org.example.demo.dal;

import org.example.demo.models.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();

    Contact findById(int contactId);

    Contact add(Contact contact);

    boolean update(Contact contact);
    boolean deleteById(int contactId);
}
