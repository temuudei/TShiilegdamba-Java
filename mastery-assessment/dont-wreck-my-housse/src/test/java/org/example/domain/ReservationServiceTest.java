package org.example.domain;

import org.example.data.*;
import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.example.ui.ConsoleIO;
import org.example.ui.View;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    static final String GUEST_DIRECTORY = "./data/guests.csv";
    static final String HOST_DIRECTORY = "./data/hosts.csv";
    static final String RESERVATION_DIRECTORY = "./data/reservation";
    ReservationService service = new ReservationService(new GuestFileRepository(GUEST_DIRECTORY), new HostFileRepository(HOST_DIRECTORY), new ReservationFileRepository(RESERVATION_DIRECTORY), new ConsoleIO(), new View(new ConsoleIO()));
    @Test
    void view() {
        //ACT
        List<Reservation> reservationList = service.view("eyearnes0@sfgate.com");
        //ASSERT
        String expected = "ab@gmail.com";
        //assertEquals(expected, reservationList.get(0).getGuest().getEmail());
    }

    @Test
    void checkDifferenceBetweenTwoDates() {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2023,04,18));
        reservation.setEndDate(LocalDate.of(2023, 04, 25));
        //List<Integer> testing = service.add(reservation);
//        assertEquals(5, testing.get(0));
//        assertEquals(2, testing.get(1));
    }

    @Test
    void checkAddMethod() throws DataException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        Guest guest = new Guest();
        guest.setEmail("slomas0@mediafire.com");
        host.setStandardRate(BigDecimal.valueOf(295));
        host.setWeekendRate(BigDecimal.valueOf(368.75));
        host.setEmail("krhodes1@posterous.com");
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2023, 05, 05));
        reservation.setEndDate(LocalDate.of(2023, 05,15));
        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }
}