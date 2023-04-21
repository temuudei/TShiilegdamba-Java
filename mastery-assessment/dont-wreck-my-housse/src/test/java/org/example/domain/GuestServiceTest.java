package org.example.domain;

import org.example.data.GuestFileRepository;
import org.example.models.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {
    static final String DIRECTORY = "./data/guests.csv";
    GuestService service = new GuestService(new GuestFileRepository(DIRECTORY));

    @Test
    void firstEmailInTheFileShouldHaveCorrespondToSullivan() {
        //ACT
        String email = "slomas0@mediafire.com";
        Guest guest = service.findByEmail(email);
        //ASSERT
        String expected = "Sullivan";
        assertEquals(expected, guest.getFirstName());
    }
}