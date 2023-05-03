package org.example.dal;

import org.example.models.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetRepositoryTest {
    PetRepository repository = new PetRepositoryImpl();

    @Test
    void should() {
        assertTrue(true);
    }

    @Test
    void shouldFindAll() {
        List<Pet> pets = repository.findAll();
        // Not really part of the test.
        // But it's nice to confirm in the console.
        System.out.println(pets);

        assertNotNull(pets);
        assertEquals(3, pets.size());
    }

    @Test
    void shouldFindById() {

        Pet expected = new Pet();
        expected.setPetId(3);
        expected.setName("Noodles");
        expected.setType("Dog");

        Pet actual = repository.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Pet pet = new Pet();
        pet.setName("Mer");
        pet.setType("Hamster");

        Pet actual = repository.add(pet);

        System.out.println(actual);
        assertNotNull(actual);
        assertTrue(actual.getPetId() > 0);
    }

    @Test
    void shouldUpdateExisting() {
        Pet pet = new Pet();
        pet.setPetId(2);
        pet.setName("Singe");
        pet.setType("Snake");

        assertTrue(repository.update(pet));
        Assertions.assertEquals(pet, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Pet pet = new Pet();
        pet.setPetId(20000);
        pet.setName("Singe");
        pet.setType("Snake");

        assertFalse(repository.update(pet));
    }

    @Test
    void shouldDeleteExisting() {
        assertTrue(repository.deleteById(4));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(40000));
    }
}