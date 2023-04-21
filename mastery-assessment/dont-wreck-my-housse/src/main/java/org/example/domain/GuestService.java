package org.example.domain;

import org.example.data.DataException;
import org.example.data.GuestRepository;
import org.example.models.Guest;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public Guest findByEmail(String email) {
        return repository.findAll().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
    //Adds a guest
    public Result<Guest> addGuest(Guest guest) throws DataException {
        Result<Guest> result = validate(guest);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.addGuest(guest));
        return result;
    }

    private Result<Guest> validate(Guest guest) {
        Result<Guest> result = validateNulls(guest);
        if (!result.isSuccess()) {
            return result;
        }
        checkDuplicates(guest,result);
        return result;
    }

    private Result<Guest> validateNulls(Guest guest) {
        Result<Guest> result = new Result<>();
        if (guest == null) {
            result.addErrorMessage("Guest must not be null.");
            return result;
        }
        if (guest.getFirstName() == null || guest.getFirstName().isBlank()) {
            result.addErrorMessage("First name cannot be empty.");
        }
        if (guest.getLastName() == null || guest.getLastName().isBlank()) {
            result.addErrorMessage("Last name cannot be empty.");
        }
        if (guest.getEmail() == null || guest.getEmail().isBlank()) {
            result.addErrorMessage("Email cannot be empty.");
        }
        if (guest.getPhone() == null || guest.getPhone().isBlank()) {
            result.addErrorMessage("Phone number cannot be empty.");
        }
        if (guest.getState() == null || guest.getState().isBlank()) {
            result.addErrorMessage("State cannot be empty.");
        }
        return result;
    }
    private void checkDuplicates(Guest guest, Result<Guest> result) {
        if (repository.findAll().stream().anyMatch(i -> i.getFirstName().equalsIgnoreCase(guest.getFirstName()) &&
                                                        i.getLastName().equalsIgnoreCase(guest.getLastName()) &&
                                                        i.getEmail().equalsIgnoreCase(guest.getEmail()) &&
                                                        i.getPhone().equalsIgnoreCase(guest.getPhone()) &&
                                                        i.getState().equalsIgnoreCase(guest.getState()))) {
            result.addErrorMessage(String.format("'%s, %s' already exists in our database.", guest.getFirstName(), guest.getLastName()));
        }
    }
}