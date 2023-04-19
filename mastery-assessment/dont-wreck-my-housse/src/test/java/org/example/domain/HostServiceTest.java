package org.example.domain;

import org.example.data.HostFileRepository;
import org.example.models.Host;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HostServiceTest {
    static final String DIRECTORY = "./data/hosts.csv";
    HostService service = new HostService(new HostFileRepository(DIRECTORY));
    @Test
    void firstEmailInTheFileShouldCorrespondToYearnes() {
        //ACT
        String email = "eyearnes0@sfgate.com";
        Host host = service.findByEmail(email);
        //ASSERT
        String expected = "Yearnes";
        assertEquals(expected, host.getLastName());
    }
}