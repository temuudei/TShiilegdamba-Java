package org.example.data;

import org.example.models.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> findAll();

    Pet findByName(String petName);

    Pet findById(int petId);

    Pet add(Pet pet);

    boolean update(Pet pet);

    boolean deleteById(int petId);
}
