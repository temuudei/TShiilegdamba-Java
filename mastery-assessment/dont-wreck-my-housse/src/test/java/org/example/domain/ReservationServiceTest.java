package org.example.domain;

import org.example.data.*;
import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.example.ui.ConsoleIO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    static final String GUEST_DIRECTORY = "./data/guests.csv";
    static final String HOST_DIRECTORY = "./data/hosts.csv";
    static final String RESERVATION_DIRECTORY = "./data/reservations";
    ReservationService service = new ReservationService(new GuestFileRepository(GUEST_DIRECTORY), new HostFileRepository(HOST_DIRECTORY), new ReservationFileRepository(RESERVATION_DIRECTORY), new ConsoleIO());
    @Test
    void shouldHaveSullivanAsFirstElement() {
        //ACT
        List<Reservation> reservationList = service.viewHostReservations("eyearnes0@sfgate.com");
        //ASSERT
        String expected = "Sullivan";
        assertEquals(expected, reservationList.get(0).getGuest().getFirstName());
    }

    @Test
    void testingDeleteMethod() throws DataException {
        //ARRANGE
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setId("acb780c9-a2bd-4c58-b007-379200805cb1");
        host.setEmail("vgaskenb@php.net");
        reservation.setId(2);
        reservation.setHost(host);
        // ACT/ASSERT
        assertEquals(service.delete(reservation).getPayload().getId(), 2);
    }

    @Test
    void checkAddMethod() throws DataException {
        //ARRANGE
        Reservation reservation = new Reservation();
        Host host = new Host();
        Guest guest = new Guest();
        guest.setEmail("slomas0@mediafire.com");
        host.setStandardRate(BigDecimal.valueOf(295));
        host.setWeekendRate(BigDecimal.valueOf(368.75));
        host.setEmail("krhodes1@posterous.com");
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2027, 10, 05));
        reservation.setEndDate(LocalDate.of(2027, 12,23));
        //ACT
        Result<Reservation> result = service.sendAddReservationInfo(reservation);
        //ASSERT
        assertFalse(!result.isSuccess());
        service.add(reservation);
    }

    @Test
    void testingUpdateMethod() throws DataException {
        //ARRANGE
        Reservation reservation = new Reservation();
        Host host = new Host();
        Guest guest = new Guest();
        guest.setEmail("slomas0@mediafire.com");
        host.setStandardRate(BigDecimal.valueOf(295));
        host.setWeekendRate(BigDecimal.valueOf(368.75));
        host.setEmail("krhodes1@posterous.com");
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2028, 10, 05));
        reservation.setEndDate(LocalDate.of(2028, 12,23));
        reservation.setId(16);
        //ACT
        Result<Reservation> result = service.sendUpdateReservationInfo(reservation);
        //ASSERT
        //assertFalse(!result.isSuccess());
        assertTrue(service.update(reservation));
    }
}