package org.example.domain;

import org.example.data.*;
import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.example.ui.ConsoleIO;
import org.example.ui.View;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReservationService {
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;
    private final ReservationRepository reservationRepository;
    private final ConsoleIO io;
    private final View view;

    public ReservationService(GuestRepository guestRepository, HostRepository hostRepository, ReservationRepository reservationRepository, ConsoleIO io, View view) {
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
        this.reservationRepository = reservationRepository;
        this.io = io;
        this.view = view;
    }

    public List<Reservation> view(String email) {
        List<Host> hostList = hostRepository.findAll();
        if (!checkIfHostExists(hostList, email) || !checkIfHostHasReservations(hostList, email)) {
            return null;
        }

        return getReservationList(hostList, email);
    }

    public Result<Reservation> update(Reservation reservation) throws DataException {
        Result<Reservation> result = validateNoOverlappingReservationsForUpdate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        Reservation rsv = makeReservation(reservation);
        if (view.showSummary(rsv.getStartDate(), rsv.getEndDate(), rsv.getGuest().getTotal())) {
            if (reservationRepository.update(reservation)) {
                result.setPayload(reservation);
            }
        } else {
            result.addMessage("No reservations have been updated. Goodbye.");
        }
        return result;
    }

    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validateNoOverlappingReservations(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        Reservation rsv = makeReservation(reservation);
        if (view.showSummary(rsv.getStartDate(), rsv.getEndDate(), rsv.getGuest().getTotal())) {
            result.setPayload(reservationRepository.add(reservation));
        } else {
            result.addMessage("No reservations have been made. Goodbye.");
        }
        return result;
    }

    public List<Reservation> showIfGuestInHostReservation(Reservation reservation) {
        List<Host> hostList = hostRepository.findAll();
        List<Reservation> reservationList = getReservationList(hostList, reservation.getHost().getEmail());
        List<Reservation> newList = new ArrayList<>();

        for (int i = 0; i < reservationList.size(); i++) {
            if (reservation.getGuest().getEmail().equalsIgnoreCase(reservationList.get(i).getGuest().getEmail())) {
                newList.add(reservationList.get(i));
            }
        }
        return newList;
    }

    public Host viewHostInfo(String email) {
        Host host = new Host();
        List<Host> hostList = hostRepository.findAll();
        for (int i = 0; i < hostList.size(); i++) {
            if (email.equalsIgnoreCase(hostList.get(i).getEmail())) {
                host.setLastName(hostList.get(i).getLastName());
                host.setAddress(hostList.get(i).getAddress());
                host.setCity(hostList.get(i).getCity());
                host.setState(hostList.get(i).getState());
                host.setPostalCode(hostList.get(i).getPostalCode());
                break;
            }
        }
        return host;
    }

    public List<Reservation> viewForAdd(String email) {
        List<Host> hostList = hostRepository.findAll();
        return getReservationList(hostList, email);
    }

    public boolean validateHostInTheFile(String email) {
        List<Host> hostList = hostRepository.findAll();
        boolean isExisting = false;
        for (int i = 0; i < hostList.size(); i++) {
            if (email.equalsIgnoreCase(hostList.get(i).getEmail())) {
                return true;
            }
        }
        return isExisting;
    }


    public boolean validateGuestInTheFile(String email) {
        List<Guest> guestList = guestRepository.findAll();
        boolean isExisting = false;
        for (int i = 0; i < guestList.size(); i++) {
            if (email.equalsIgnoreCase(guestList.get(i).getEmail())) {
                return true;
            }
        }
        return isExisting;
    }

    private Reservation makeReservation(Reservation reservation) {
        Host host = getHostInfo(reservation);
        Guest guest = getGuestInfo(reservation);
        guest.setTotal(getTotalPrice(reservation));
        reservation.setHost(host);
        reservation.setGuest(guest);
        return reservation;
    }

    private List<Reservation> getReservationList(List<Host> hostList, String email) {
        List<Reservation> reservationList = new ArrayList<>();
        for (int i = 0; i < hostList.size(); i++) {
            if (email.equalsIgnoreCase(hostList.get(i).getEmail())) {
                reservationList = reservationRepository.findById(hostList.get(i));
                break;
            }
        }
        return reservationList;
    }

    private Host getHostInfo(Reservation reservation) {
        List<Host> hostList = hostRepository.findAll();
        Host host = new Host();
        for (int i = 0; i < hostList.size(); i++) {
            if (reservation.getHost().getEmail().equalsIgnoreCase(hostList.get(i).getEmail())) {
                host.setId(hostList.get(i).getId());
                host.setAddress(hostList.get(i).getAddress());
                host.setCity(hostList.get(i).getCity());
                host.setState(hostList.get(i).getState());
                host.setPostalCode(hostList.get(i).getPostalCode());
            }
        }
        return host;
    }

    private Guest getGuestInfo(Reservation reservation) {
        List<Guest> guestList = guestRepository.findAll();
        Guest guest = new Guest();
        for (int i = 0; i < guestList.size(); i++) {
            if (reservation.getGuest().getEmail().equalsIgnoreCase(guestList.get(i).getEmail())) {
                guest.setFirstName(guestList.get(i).getFirstName());
                guest.setLastName(guestList.get(i).getLastName());
                guest.setPhone(guestList.get(i).getPhone());
                guest.setEmail(guestList.get(i).getEmail());
            }
        }
        return guest;
    }

    private BigDecimal getTotalPrice(Reservation reservation) {
        List<Host> hostList = hostRepository.findAll();
        List<Long> totalDates = getDateDifferences(reservation);

        BigDecimal weekendRate = new BigDecimal(0);
        BigDecimal standardRate = new BigDecimal(0);
        long numberOfWeekdays = totalDates.get(0);
        long numberOfWeekends = totalDates.get(1);

        for (int i = 0; i < hostList.size(); i++) {
            if (reservation.getHost().getEmail().equalsIgnoreCase(hostList.get(i).getEmail())) {
                weekendRate = hostList.get(i).getWeekendRate();
                standardRate = hostList.get(i).getStandardRate();
                break;
            }
        }
        return (weekendRate.multiply(BigDecimal.valueOf(numberOfWeekends))).add(standardRate.multiply(BigDecimal.valueOf(numberOfWeekdays)));
    }

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

    private Result<Reservation> validateNoOverlappingReservations(Reservation reservation) {
        Result<Reservation> result = new Result<>();
        List<Host> hostList = hostRepository.findAll();
        List<Reservation> reservations = getReservationList(hostList, reservation.getHost().getEmail());
        Collections.sort(reservations, Comparator.comparing(Reservation::getStartDate));

        for (int i = 0; i < reservations.size(); i++) {
            if (reservation.getStartDate().equals(reservations.get(i).getStartDate()) || reservation.getStartDate().equals(reservations.get(i).getEndDate()) ||
                reservation.getEndDate().equals(reservations.get(i).getEndDate()) || reservation.getEndDate().equals(reservations.get(i).getStartDate()) ||
               (reservation.getStartDate().isAfter(reservations.get(i).getStartDate()) && reservation.getStartDate().isBefore(reservations.get(i).getEndDate())) ||
               (reservation.getStartDate().isBefore(reservations.get(i).getStartDate()) &&
                reservation.getEndDate().isAfter(reservations.get(reservations.size() - 1).getEndDate()))) {
                result.addErrorMessage("Provided dates overlap with other reservations. Please choose other dates.");
                return result;
            }
        }
        return result;
    }

    private Result<Reservation> validateNoOverlappingReservationsForUpdate(Reservation reservation) {
        Result<Reservation> result = new Result<>();
        List<Host> hostList = hostRepository.findAll();
        List<Reservation> reservations = getReservationList(hostList, reservation.getHost().getEmail());
        Collections.sort(reservations, Comparator.comparing(Reservation::getStartDate));

        if (reservations.size() > 1 ) {
            for (int i = 0; i < reservations.size(); i++) {
                if (reservation.getEndDate().equals(reservations.get(i).getStartDate()) ||
                    reservation.getStartDate().equals(reservations.get(i).getEndDate()) ||
                   (reservation.getEndDate().isAfter(reservations.get(i).getStartDate()) &&
                   (reservation.getEndDate().isBefore(reservations.get(i).getEndDate()) ||
                    reservation.getEndDate().equals(reservations.get(i).getEndDate()))) ||
                   (reservation.getStartDate().isBefore(reservations.get(i).getStartDate()) &&
                    reservation.getEndDate().isAfter(reservations.get(reservations.size() - 1).getEndDate())) ||
                   (reservation.getStartDate().isBefore(reservations.get(i).getEndDate()) &&
                    reservation.getEndDate().isAfter(reservations.get(reservations.size() - 1).getEndDate()))) {
                    result.addErrorMessage("Provided dates overlap with other reservations. Please choose other dates.");
                    return result;
                }
            }
        }
        return result;
    }

    private boolean checkIfHostExists(List<Host> hostList, String email) {
        boolean isExisting = true;
        String message = "";
        for (int i = 0; i < hostList.size(); i++) {
            if (email.equalsIgnoreCase(hostList.get(i).getEmail())) {
                return true;
            }
            message = "Sorry, '" + email + "' does not exist in the database";
            isExisting = false;
        }
        io.println(message);
        return isExisting;
    }

    private boolean checkIfHostHasReservations(List<Host> hostList, String email) {
        boolean hasReservation = true;
        String message = "";
        for (int i = 0; i < hostList.size(); i++) {
            if (email.equalsIgnoreCase(hostList.get(i).getEmail())) {
                List<Reservation> reservationList = reservationRepository.findById(hostList.get(i));
                if (reservationList == null || reservationList.isEmpty()) {
                    message = "Sorry, '" + hostList.get(i).getLastName() + ", " + hostList.get(i).getEmail() + "' does not have any reservations at the moment.";
                    hasReservation = false;
                }
            }
        }
        io.println(message);
        return hasReservation;
    }
}