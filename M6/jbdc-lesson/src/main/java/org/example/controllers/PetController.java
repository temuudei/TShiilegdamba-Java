package org.example.controllers;

import org.example.domains.PetService;
import org.example.models.Pet;
import org.example.domains.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/pets")
public class PetController{

    PetService service;
    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> findAll() {
        return service.findAll();
    }

    @GetMapping("/{petId}")
    public Pet findById(@PathVariable int petId) {
        return service.findById(petId);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Pet pet) {
        Result<Pet> result = service.add(pet);
        if(result.isSuccessful()){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getMessages().get(0), HttpStatus.PRECONDITION_FAILED);
        }
    }

}