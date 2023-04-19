package org.example.data;

import org.example.models.Guest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {
    static final String DIRECTORY = "./data/guests.csv";
    static final int GUEST_COUNT = 1000;
    GuestFileRepository guestFileRepository = new GuestFileRepository(DIRECTORY);

    @Test
    void guestSizeShouldBeOneThousand() {
        //ACT
        List<Guest> guests = guestFileRepository.findAll();
        //ASSERT
        assertEquals(GUEST_COUNT, guests.size());
    }

    @Test
    void veryLastFirstNameInTheFileShouldEqualToCarrissa() {
        //ACT
        List<Guest> guests = guestFileRepository.findAll();
        String expected = "Carrissa";
        //ASSERT
        assertEquals(expected, guests.get(guests.size() - 1).getFirstName());
    }

    @Test
    void veryFirstLastNameInTheFileShouldEqualToBracher() {
        //ACT
        List<Guest> guests = guestFileRepository.findAll();
        String expected = "Bracher";
        //ASSERT
        assertEquals(expected, guests.get(guests.size() - 1).getLastName());
    }
}