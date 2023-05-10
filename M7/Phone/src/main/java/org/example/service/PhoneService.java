package org.example.service;

import org.example.data.PhoneJdbcTemplateRepository;
import org.example.models.Phone;

import java.util.List;

public class PhoneService {
    private final PhoneJdbcTemplateRepository repository;

    public PhoneService(PhoneJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Phone> findAll() {
        return repository.findAll();
    }

    public Phone findById(int id) {
        return repository.findById(id);
    }

    public Result<Phone> add(Phone phone) {
        Result<Phone> result =
    }

    private Result<Phone> validate(Phone phone) {
        Result<Phone> result = new Result<>();
        if (phone == null) {
            return result;
        }

        if (phone.getPhoneName() == null || phone.getPhoneName().isBlank()) {
            result.addMessage("Phone name cannot be null or empty", ResultType.INVALID);
        }

        if (phone.getPhoneOS() == null || phone.getPhoneOS().isBlank()) {
            result.addMessage("Phone operating system cannot be null or empty", ResultType.INVALID);
        }
        return result;
    }
}
