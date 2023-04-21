package org.example.data;

import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {
    static final String DIRECTORY = "./data/reservations";
    ReservationFileRepository repository = new ReservationFileRepository(DIRECTORY);

    @Test
    void firstElementFirstNameShouldEqualToSulliVan() {
        //ARRANGE
        Host host = new Host();
        host.setId("3edda6bc-ab95-49a8-8962-d50b53f84b15");
        //ACT
        List<Reservation> reservationList = repository.findById(host);
        //ASSERT
        assertEquals("Sullivan", reservationList.get(0).getGuest().getFirstName());
    }

    @Test
    void testingAddMethod() throws DataException {
        //ARRANGE
        Host host = new Host();
        Guest guest = new Guest();
        Reservation reservation = new Reservation();
        host.setId("acb780c9-a2bd-4c58-b007-379200805cb1");
        guest.setFirstName("John");
        guest.setLastName("Whick");
        guest.setEmail("johnwhich@gmail.com");
        guest.setPhone("911");
        guest.setTotal(BigDecimal.valueOf(242));
        reservation.setStartDate(LocalDate.of(2025, 5, 21));
        reservation.setEndDate(LocalDate.of(2025, 5, 30));
        reservation.setHost(host);
        reservation.setGuest(guest);
        //ACT
        repository.add(reservation);
        //ASSERT
        host.setId("acb780c9-a2bd-4c58-b007-379200805cb1");
        List<Reservation> reservationList = repository.findById(host);
        assertEquals("John", reservationList.get(0).getGuest().getFirstName());
    }

    @Test
    void testingUpdateMethod() throws DataException {
        //ARRANGE
        Reservation reservation = new Reservation();
        Host host = new Host();
        Guest guest = new Guest();
        host.setId("acb780c9-a2bd-4c58-b007-379200805cb1");
        guest.setFirstName("John");
        guest.setLastName("Whick");
        guest.setEmail("johnwhich@gmail.com");
        guest.setPhone("911");
        guest.setTotal(BigDecimal.valueOf(242));
        reservation.setStartDate(LocalDate.of(2023, 10, 21));
        reservation.setEndDate(LocalDate.of(2023, 12, 30));
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setId(2);
        //ACT/ASSERT
        assertTrue(repository.update(reservation));
    }

    @Test
    void testingDeleteMethod() throws DataException {
        //ARRANGE
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setId("acb780c9-a2bd-4c58-b007-379200805cb1");
        reservation.setId(2);
        reservation.setHost(host);
        //ACT/ASSERT
        assertTrue(repository.delete(reservation));
    }
}