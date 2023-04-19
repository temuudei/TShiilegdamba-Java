package org.example.ui;

import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public void hostAndGuestNotInFile(String hostEmail, String guestEmail) {
        io.printf("Unfortunately, '%s' or '%s' or both do not exist in the file. Therefore, reservation cannot be created.\n", hostEmail, guestEmail);
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
        boolean input = io.readBoolean("Is this okay? [y/n]: ");
        if (input == true) {
            return true;
        }
        return false;
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayReservations(List<Reservation> reservations) {
        if (reservations != null) {
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
    }
    public void displayFutureReservations(List<Reservation> reservations) {
        if (reservations != null) {
            for (int i = 0; i < reservations.size(); i++) {
                LocalDate startDate = reservations.get(i).getStartDate();
                LocalDate endDate = reservations.get(i).getStartDate();
                while (startDate.isAfter(LocalDate.now()) && endDate.isAfter(LocalDate.now())) {
                    io.printf("ID: %s, %s - %s, Guest: first name: %s, last name: %s, email: %s, phone number: %s, total price: $%.2f \n",
                            reservations.get(i).getId(),
                            reservations.get(i).getStartDate(),
                            reservations.get(i).getEndDate(),
                            reservations.get(i).getGuest().getFirstName(),
                            reservations.get(i).getGuest().getLastName(),
                            reservations.get(i).getGuest().getEmail(),
                            reservations.get(i).getGuest().getPhone(),
                            reservations.get(i).getGuest().getTotal()
                    );
                    break;
                }
            }
        }
    }
    public LocalDate getStartDate() {
        LocalDate startDate;
        do {
            startDate = io.readLocalDate("Select a start date [MM/dd/yyyy]: ");
            if (startDate.isBefore(LocalDate.now().plusDays(1))) {
                displayStatus(false, "Start date much be in the future.");
            }
        } while(startDate.isBefore(LocalDate.now().plusDays(1)));
        return startDate;
    }

    public LocalDate getEndDate(LocalDate startDate) {
        LocalDate endDate;
        do {
            endDate = io.readLocalDate("Select an end date [MM/dd/yyyy]: ");
            if (endDate.isBefore(startDate.plusDays(1))) {
                displayStatus(false, "End date must come after start date.");
            }
        } while(endDate.isBefore(startDate.plusDays(1)));
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
}
