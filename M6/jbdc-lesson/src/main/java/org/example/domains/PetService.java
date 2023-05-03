package org.example.domains;

import org.example.dal.PetRepository;
import org.example.models.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> findAll() {
        return repository.findAll();
    }

    public Pet findById(int pet_id) {
        return repository.findById(pet_id);
    }

    public Result<Pet> add(Pet pet) {
        Result<Pet> result = new Result<>();
        if (pet == null) {
            result.addMessage("Object is null");
            return result;
        }

        if(pet.getName() == null || pet.getType() == null) {
            result.addMessage("Object fields are empty");
            return result;
        }

        pet = repository.add(pet);
        result.setObj(pet);

        return result;
    }
}
