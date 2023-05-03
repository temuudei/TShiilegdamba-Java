package org.example.controller;

import org.example.dal.ContactRepository;
import org.example.models.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @GetMapping("/{contactId}")
    public Contact findById(@PathVariable int contactId) {
        return contactRepository.findById(contactId);
    }

    @PostMapping
    public Contact add(@RequestBody Contact contact) {
        return contactRepository.add(contact);
    }

//    @PutMapping("/{contactId}")
//    public ResponseEntity<String> update(@PathVariable int contactId, @RequestBody Contact contact) {
//        boolean success = contactRepository.update(contact);
//        if (success) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>("Bad ")
//        }
//    }
}
