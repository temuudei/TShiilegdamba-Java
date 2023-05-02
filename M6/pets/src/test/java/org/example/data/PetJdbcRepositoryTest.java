package org.example.data;

import org.example.models.Pet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetJdbcRepositoryTest {

    PetRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PetRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should() {
        assertTrue(true);
    }

    @Test
    void shouldFindApp() {
        List<Pet> pets = repository.findAll();
        System.out.println(pets);
        assertNotNull(pets);
        assertEquals(3, pets.size());
    }

    @Test
    void shouldFineOnePet() {
        Pet pet = repository.findByName("Meep");
        assertNotNull(pet);
        assertEquals("Meep", pet.getName());
    }

    @Test
    void shouldFindById() {
        Pet pet = repository.findById(3);
        Pet expected = new Pet();
        expected.setType("Dog");
        expected.setName("Noodles");
        expected.setPetId(3);
        assertEquals(expected, pet);
    }

    @Test
    void shouldAdd() {
        Pet pet = new Pet();
        pet.setName("Mer");
        pet.setType("Hamster");
        Pet actual = repository.add(pet);
        System.out.println(actual);
        assertTrue(actual.getPetId() > 0);
    }

    @Test
    void shouldUpdateExistingPet() {
        Pet pet = new Pet();
        pet.setPetId(2);
        pet.setName("Singe");
        pet.setType("Snake");
        assertTrue(repository.update(pet));
        assertEquals(pet, repository.findById(2));
    }

    @Test
    void shouldNotUpdateNonExistingPet() {
        Pet pet = new Pet();
        pet.setPetId(20000);
        pet.setName("Singe");
        pet.setType("Snake");
        assertFalse(repository.update(pet));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(4));
    }

    @Test
    void shouldNotDelete() {
        assertFalse(repository.delete(40000));
    }
}