package org.example.data;

import org.example.models.Host;
import org.example.models.Reservation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {
    static final String DIRECTORY= "./data/reservations";
    ReservationFileRepository repository = new ReservationFileRepository(DIRECTORY);
    @Test
    void findById() {
        Host host = new Host();
        host.setId("3edda6bc-ab95-49a8-8962-d50b53f84b15");
        List<Reservation> reservationList = repository.findById(host);
        System.out.println(reservationList.get(0).getGuest().getFirstName());
        assertEquals("A", reservationList.get(0).getGuest().getFirstName());
        System.out.println(reservationList.size());
    }
}