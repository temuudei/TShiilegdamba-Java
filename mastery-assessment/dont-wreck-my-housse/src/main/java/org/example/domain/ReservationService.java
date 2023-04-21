package org.example.domain;

import org.example.data.*;
import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.example.ui.ConsoleIO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

import static java.time.DayOfWeek.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReservationService {
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;
    private final ReservationRepository reservationRepository;
    private final ConsoleIO io;

    public ReservationService(GuestRepository guestRepository, HostRepository hostRepository, ReservationRepository reservationRepository, ConsoleIO io) {
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
        this.reservationRepository = reservationRepository;
        this.io = io;
    }

    //Shows host reservations
    public List<Reservation> viewHostReservations(String email) {
        List<Host> hostList = hostRepository.findAll();
        if (!checkIfHostExists(hostList, email) || !checkIfHostHasReservations(hostList, email)) {
            return null;
        }
        return getReservationList(hostList, email);
    }

    //Deletes a reservation
    public Result<Reservation> delete(Reservation reservation) throws DataException {
        Result<Reservation> result = new Result<>();
        reservation.setHost(getHostInfo(reservation));
        if (!reservationRepository.delete(reservation)) {
            result.addErrorMessage(String.format("Reservation %d was not deleted", reservation.getId()));
        }
        result.setPayload(reservation);
        return result;
    }

    //Updates a reservation
    public boolean update(Reservation reservation) throws DataException {
        return reservationRepository.update(reservation);
    }

    //Adds a reservation
    public void add(Reservation reservation) throws DataException {
        reservationRepository.add(reservation);
    }

    //Verifies reservation before updating it
    public Result<Reservation> sendUpdateReservationInfo(Reservation reservation) {
        Result<Reservation> result = validateNoOverlappingReservationsForUpdate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        Reservation rsv = makeReservation(reservation);
        result.setPayload(rsv);
        return result;
    }

    //Verifies reservation before adding it
    public Result<Reservation> sendAddReservationInfo(Reservation reservation) {
        Result<Reservation> result = validateNoOverlappingReservations(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        Reservation rsv = makeReservation(reservation);
        result.setPayload(rsv);
        return result;
    }

    //Checks if guest and host have a reservation together
    public List<Reservation> showIfGuestInHostReservation(Reservation reservation) {
        List<Host> hostList = hostRepository.findAll();
        List<Reservation> reservationList = getReservationList(hostList, reservation.getHost().getEmail());
        List<Reservation> newList = new ArrayList<>();

        for (Reservation value : reservationList) {
            if (reservation.getGuest().getEmail().equalsIgnoreCase(value.getGuest().getEmail())) {
                newList.add(value);
            }
        }
        return newList;
    }

    //Gets Host info from the file
    public Host viewHostInfo(String email) {
        Host host = new Host();
        List<Host> hostList = hostRepository.findAll();
        for (Host value : hostList) {
            if (email.equalsIgnoreCase(value.getEmail())) {
                host.setLastName(value.getLastName());
                host.setAddress(value.getAddress());
                host.setCity(value.getCity());
                host.setState(value.getState());
                host.setPostalCode(value.getPostalCode());
                break;
            }
        }
        return host;
    }

    //Used to display reservations
    public List<Reservation> viewForAdd(String email) {
        List<Host> hostList = hostRepository.findAll();
        return getReservationList(hostList, email);
    }

    //Used to check if host is in the file
    public boolean validateHostInTheFile(String email) {
        List<Host> hostList = hostRepository.findAll();
        return IntStream.range(0, hostList.size()).anyMatch(i -> email.equalsIgnoreCase(hostList.get(i).getEmail()));
    }

    //Used to check if the guest is in the file
    public boolean validateGuestInTheFile(String email) {
        List<Guest> guestList = guestRepository.findAll();
        return IntStream.range(0, guestList.size()).anyMatch(i -> email.equalsIgnoreCase(guestList.get(i).getEmail()));
    }

    //Collects all the important reservation info
    private Reservation makeReservation(Reservation reservation) {
        Host host = getHostInfo(reservation);
        Guest guest = getGuestInfo(reservation);
        guest.setTotal(getTotalPrice(reservation));
        reservation.setHost(host);
        reservation.setGuest(guest);
        return reservation;
    }

    //Gets all reservations for a specific host
    private List<Reservation> getReservationList(List<Host> hostList, String email) {
        List<Reservation> reservationList = new ArrayList<>();
        for (Host host : hostList) {
            if (email.equalsIgnoreCase(host.getEmail())) {
                reservationList = reservationRepository.findById(host);
                break;
            }
        }
        return reservationList;
    }

    //Gets host info
    private Host getHostInfo(Reservation reservation) {
        List<Host> hostList = hostRepository.findAll();
        Host host = new Host();
        for (Host value : hostList) {
            if (reservation.getHost().getEmail().equalsIgnoreCase(value.getEmail())) {
                host.setId(value.getId());
                host.setAddress(value.getAddress());
                host.setCity(value.getCity());
                host.setState(value.getState());
                host.setPostalCode(value.getPostalCode());
                break;
            }
        }
        return host;
    }

    //Gets guest info
    private Guest getGuestInfo(Reservation reservation) {
        List<Guest> guestList = guestRepository.findAll();
        Guest guest = new Guest();
        for (Guest value : guestList) {
            if (reservation.getGuest().getEmail().equalsIgnoreCase(value.getEmail())) {
                guest.setFirstName(value.getFirstName());
                guest.setLastName(value.getLastName());
                guest.setPhone(value.getPhone());
                guest.setEmail(value.getEmail());
                break;
            }
        }
        return guest;
    }

    //Calculates total price
    private BigDecimal getTotalPrice(Reservation reservation) {
        List<Host> hostList = hostRepository.findAll();
        List<Long> totalDates = getDateDifferences(reservation);

        BigDecimal weekendRate = new BigDecimal(0);
        BigDecimal standardRate = new BigDecimal(0);
        long numberOfWeekdays = totalDates.get(0);
        long numberOfWeekends = totalDates.get(1);

        for (Host host : hostList) {
            if (reservation.getHost().getEmail().equalsIgnoreCase(host.getEmail())) {
                weekendRate = host.getWeekendRate();
                standardRate = host.getStandardRate();
                break;
            }
        }
        return (weekendRate.multiply(BigDecimal.valueOf(numberOfWeekends))).add(standardRate.multiply(BigDecimal.valueOf(numberOfWeekdays)));
    }

    //Calculates number of weekdays and weekends between two dates
    private List<Long> getDateDifferences(Reservation reservation) {
        List<Long> totalDates = new ArrayList<>();
        LocalDate startDate = reservation.getStartDate();
        LocalDate endDate = reservation.getEndDate();
        long difference = DAYS.between(startDate, endDate);

        final Set<DayOfWeek> businessDays = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
        List<LocalDate> dates = startDate.datesUntil(endDate).filter(t -> businessDays.contains(t.getDayOfWeek())).toList();
        long weekends = difference - dates.size();
        long weekdays = dates.size();
        totalDates.add(weekdays);
        totalDates.add(weekends);
        return totalDates;
    }

    //Checks for overlapping reservations when adding
    private Result<Reservation> validateNoOverlappingReservations(Reservation reservation) {
        Result<Reservation> result = new Result<>();
        List<Host> hostList = hostRepository.findAll();
        List<Reservation> reservations = getReservationList(hostList, reservation.getHost().getEmail());
        for (int i = 0; i < reservations.size(); i++) {
            if (reservation.getStartDate().isEqual(reservations.get(i).getStartDate()) ||
                    reservation.getStartDate().isEqual(reservations.get(i).getEndDate()) ||
                    (reservation.getStartDate().isBefore(reservations.get(i).getEndDate()) &&
                            reservation.getStartDate().isAfter(reservations.get(i).getStartDate())) ||
                    reservation.getEndDate().isEqual(reservations.get(i).getStartDate()) ||
                    reservation.getEndDate().isEqual(reservations.get(i).getEndDate()) ||
                    (reservation.getEndDate().isBefore(reservations.get(i).getEndDate()) &&
                            reservation.getEndDate().isAfter(reservations.get(i).getStartDate())) ||
                    (reservation.getStartDate().isBefore(reservations.get(i).getEndDate()) &&
                            reservation.getEndDate().isAfter(reservations.get(i).getStartDate()))) {
                result.addErrorMessage("Provided dates overlap with other reservations. Please choose other dates.");
                return result;
            }
        }
        return result;
    }

    //Checks for overlapping reservations when updating
    private Result<Reservation> validateNoOverlappingReservationsForUpdate(Reservation reservation) {
        Result<Reservation> result = new Result<>();
        List<Reservation> reservations = showIfGuestInHostReservation(reservation);
        if (reservations.size() > 1) {
            for (int i = 0; i < reservations.size(); i++) {
                if (reservation.getEndDate().isEqual(reservations.get(i).getStartDate()) ||
                        reservation.getEndDate().isEqual(reservations.get(i).getEndDate())) {
                    result.addErrorMessage("Provided dates overlap with other reservations. Please choose other dates.");
                    return result;
                }
            }
        }
        return result;
    }

    //Checks if host exists in the file
    private boolean checkIfHostExists(List<Host> hostList, String email) {
        boolean isExisting = true;
        String message = "";
        for (Host host : hostList) {
            if (email.equalsIgnoreCase(host.getEmail())) {
                return true;
            }
            message = "Sorry, '" + email + "' does not exist in the database";
            isExisting = false;
        }
        io.println(message);
        return isExisting;
    }

    //Checks if host has any reservations
    private boolean checkIfHostHasReservations(List<Host> hostList, String email) {
        boolean hasReservation = true;
        String message = "";
        for (Host host : hostList) {
            if (email.equalsIgnoreCase(host.getEmail())) {
                List<Reservation> reservationList = reservationRepository.findById(host);
                if (reservationList == null || reservationList.isEmpty()) {
                    message = "Sorry, '" + host.getLastName() + ", " + host.getEmail() + "' does not have any reservations at the moment.";
                    hasReservation = false;
                }
            }
        }
        io.println(message);
        return hasReservation;
    }
}