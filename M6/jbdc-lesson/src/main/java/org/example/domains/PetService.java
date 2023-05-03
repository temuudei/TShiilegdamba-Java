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
        validatePet(pet, result);
        if(!result.isSuccessful()) return result;
        pet = repository.add(pet);
        result.setObj(pet);
        return result;
    }
    public Result<Pet> update(int pet_id, Pet pet) {
        Result<Pet> result = new Result<>();
        validatePet(pet, result);
        if(pet_id != pet.getPetId()) {
            result.addMessage("Path id and object id do not match");
            return result;
        }
        if(!repository.update(pet)) {
            result.addMessage("Update failure, no item under that id");
            return result;
        }
        result.setObj(pet);
        return result;
    }
    public Result<Integer> delete(int petId) {
        Result<Integer> result = new Result<>();
        if(!repository.deleteById(petId)) {
            result.addMessage("Could not delete, petId does not exist");
            return result;
        }
        result.setObj(petId);
        return result;
    }
    private static void validatePet(Pet pet, Result<Pet> result) {
        if (pet == null) {
            result.addMessage("Object is null");
        }
        if(pet.getName() == null || pet.getType() == null) {
            result.addMessage("Object fields are empty");
        }
    }
}