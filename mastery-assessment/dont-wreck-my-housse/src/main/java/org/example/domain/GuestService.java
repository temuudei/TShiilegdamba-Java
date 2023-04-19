package org.example.domain;

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
}
