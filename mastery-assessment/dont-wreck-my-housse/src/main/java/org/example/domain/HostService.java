package org.example.domain;

import org.example.data.HostRepository;
import org.example.models.Host;
import org.springframework.stereotype.Service;

@Service
public class HostService {
    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public Host findByEmail(String email) {
        return repository.findAll().stream()
                                   .filter(i -> i.getEmail().equalsIgnoreCase(email))
                                   .findFirst()
                                   .orElse(null);
    }
}
