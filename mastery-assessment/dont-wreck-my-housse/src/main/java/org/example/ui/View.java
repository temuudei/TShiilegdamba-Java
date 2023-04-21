package org.example.ui;

import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class View {
    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption option : MainMenuOption.values()) {
            io.printf("%s. %s%n", option.getValue(), option.getMessage());
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max);
        return MainMenuOption.fromValue(io.readInt(message, min, max));
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }

    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public String getHostEmail() {
        return io.readRequiredString("Please enter host's email: ");
    }

    public String getGuestEmail() {
        return io.readRequiredString("Please enter guest's email: ");
    }

    public void getHostAndGuestNotInFile(String hostEmail, String guestEmail) {
        io.printf("Unfortunately, '%s' or '%s' or both do not exist in the file. Therefore, reservation cannot be created.\n", hostEmail, guestEmail);
    }

    public void getHostAndGuestNoReservation(String hostEmail, String guestEmail) {
        io.printf("Unfortunately, '%s' or '%s' do not have a reservation together. Please make a new reservation instead.\n", hostEmail, guestEmail);
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayCancellation(boolean success, List<String> messages) {
        displayHeader(success ? "Confirmed" : "Not confirmed");
        for (String message : messages) {
            io.println(message);
        }
    }

    public boolean showSummary(LocalDate startDate, LocalDate endDate, BigDecimal total) {
        displayHeader("Summary");
        io.println("Start: " + startDate);
        io.println("End: " + endDate);
        io.println("Total: $" + total);
        return io.readBoolean("Is this okay? [y/n]: ");
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayCancellation(boolean success, String mesaage) {
        displayCancellation(success, List.of(mesaage));
    }

    public void displayReservations(List<Reservation> reservations) {
        if (reservations != null) {
            reservations.sort(Comparator.comparing(Reservation::getStartDate));
            for (Reservation reservation : reservations) {
                io.printf("ID: %s, %s - %s, Guest: first name: %s, last name: %s, email: %s, phone number: %s, total price: $%.2f \n",
                        reservation.getId(),
                        reservation.getStartDate(),
                        reservation.getEndDate(),
                        reservation.getGuest().getFirstName(),
                        reservation.getGuest().getLastName(),
                        reservation.getGuest().getEmail(),
                        reservation.getGuest().getPhone(),
                        reservation.getGuest().getTotal()
                );
            }
        }
        io.println("");
    }

    public void displayFutureReservations(List<Reservation> reservations) {
        if (reservations != null) {
            reservations.sort(Comparator.comparing(Reservation::getStartDate));
            for (Reservation reservation : reservations) {
                LocalDate startDate = reservation.getStartDate();
                LocalDate endDate = reservation.getStartDate();
                while (startDate.isAfter(LocalDate.now()) && endDate.isAfter(LocalDate.now())) {
                    io.printf("ID: %s, %s - %s, Guest: first name: %s, last name: %s, email: %s, phone number: %s, total price: $%.2f \n",
                            reservation.getId(),
                            reservation.getStartDate(),
                            reservation.getEndDate(),
                            reservation.getGuest().getFirstName(),
                            reservation.getGuest().getLastName(),
                            reservation.getGuest().getEmail(),
                            reservation.getGuest().getPhone(),
                            reservation.getGuest().getTotal()
                    );
                    break;
                }
            }
        }
        io.println("");
    }

    public String showHostInfo(Host host) {
        return String.format("%s: %s, %s, %s %d", host.getLastName(), host.getAddress(), host.getCity(), host.getState(), host.getPostalCode());
    }

    public LocalDate getStartDate() {
        LocalDate startDate;
        do {
            startDate = io.readLocalDate("Select a start date [MM/dd/yyyy]: ");
            if (startDate.isBefore(LocalDate.now().plusDays(1))) {
                displayStatus(false, "Start date much be in the future.");
            }
        } while (startDate.isBefore(LocalDate.now().plusDays(1)));
        return startDate;
    }

    public LocalDate getEndDate(LocalDate startDate) {
        LocalDate endDate;
        do {
            endDate = io.readLocalDate("Select an end date [MM/dd/yyyy]: ");
            if (endDate.isBefore(startDate.plusDays(1))) {
                displayStatus(false, "End date must come after start date.");
            }
        } while (endDate.isBefore(startDate.plusDays(1)));
        return endDate;
    }

    public Reservation makeReservation(String guestEmail, String hostEmail, LocalDate endDate, LocalDate startDate) {
        Reservation reservation = new Reservation();
        Guest guest = new Guest();
        Host host = new Host();
        guest.setEmail(guestEmail);
        host.setEmail(hostEmail);
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        return reservation;
    }

    public Reservation makeReservation(String guestEmail, String hostEmail, LocalDate endDate, LocalDate startDate, int id) {
        Reservation reservation = new Reservation();
        Guest guest = new Guest();
        Host host = new Host();
        guest.setEmail(guestEmail);
        host.setEmail(hostEmail);
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setId(id);
        return reservation;
    }

    public Guest createGuest() {
        Guest guest = new Guest();
        guest.setFirstName(io.readRequiredString("Please enter your first name: "));
        guest.setLastName(io.readRequiredString("Please enter your last name: "));
        guest.setEmail(io.readRequiredString("Please enter your email: "));
        guest.setPhone(io.readRequiredString("Please enter your phone number: "));
        guest.setState(io.readRequiredString("Please enter your state: "));
        return guest;
    }

    public Host createHost() {
        Host host = new Host();
        host.setLastName(io.readRequiredString("Please enter your last name: "));
        host.setEmail(io.readRequiredString("Please enter your email: "));
        host.setPhone(io.readRequiredString("Please enter your phone number: "));
        host.setAddress(io.readRequiredString("Please enter your street address: "));
        host.setCity(io.readRequiredString("Please enter your your city: "));
        host.setState(io.readRequiredString("Please enter your state: "));
        host.setPostalCode(io.readInt("Please enter your postal code: "));
        host.setStandardRate(io.readBigDecimal("Please enter your standard rate: "));
        host.setWeekendRate(io.readBigDecimal("Please enter your weekend rate: "));
        return host;
    }

    public Reservation makeReservation(String guestEmail, String hostEmail, int id) {
        Reservation reservation = new Reservation();
        Guest guest = new Guest();
        Host host = new Host();
        guest.setEmail(guestEmail);
        host.setEmail(hostEmail);
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setId(id);
        return reservation;
    }


    public Reservation makeReservation(String guestEmail, String hostEmail) {
        Reservation reservation = new Reservation();
        Guest guest = new Guest();
        Host host = new Host();
        guest.setEmail(guestEmail);
        host.setEmail(hostEmail);
        reservation.setGuest(guest);
        reservation.setHost(host);
        return reservation;
    }

    public boolean validateIdInTheFile(List<Reservation> reservations, int id) {
        boolean isExisting = true;
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return true;
            }
            isExisting = false;
        }
        return isExisting;
    }

    public void displayEditingID(int id) {
        displayHeader("Editing Reservation " + id);
    }

    public int getReservationID() {
        return io.readInt("Choose the reservation ID to be changed: ");
    }

    public int getReservationIDForDelete() {
        return io.readInt("Choose the reservation ID to be deleted: ");
    }
}
