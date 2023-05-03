package org.example.demo.controller;

import org.example.demo.dal.ContactRepository;
import org.example.demo.models.Contact;
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

//    @PostMapping
//    public ResponseEntity<String> add(@RequestBody Contact contact) {
//        boolean success = contactRepository.add(contact);
//        if (success) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        else {
//
//        }
//    }

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
