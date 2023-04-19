package org.example.ui;

import org.example.data.DataException;
import org.example.domain.GuestService;
import org.example.domain.HostService;
import org.example.domain.ReservationService;
import org.example.domain.Result;
import org.example.models.Guest;
import org.example.models.Host;
import org.example.models.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class Controller {
    private final GuestService guestService;
    private final HostService hostService;
    private final ReservationService reservationService;
    private final View view;

    public Controller(GuestService guestService, HostService hostService, ReservationService reservationService, View view) {
        this.guestService = guestService;
        this.hostService = hostService;
        this.reservationService = reservationService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to Don't Wreck My House!");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_RESERVATIONS_FOR_HOST:
                    viewReservations();
                    break;
                case MAKE_RESERVATION:
                    makeReservation();
                    break;
                case EDIT_RESERVATION:
                    editReservation();
                    break;
                case CANCEL_RESERVATION:
                    cancelReservation();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void viewReservations() {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_FOR_HOST.getMessage());
        String email = view.getHostEmail();
        List<Reservation> reservations = reservationService.view(email);
        view.displayReservations(reservations);
        view.enterToContinue();
    }

    private void makeReservation() throws DataException {
        view.displayHeader(MainMenuOption.MAKE_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        String hostEmail = view.getHostEmail();

        if (reservationService.validateHostInTheFile(hostEmail) && reservationService.validateGuestInTheFile(guestEmail)) {
            view.displayFutureReservations(reservationService.viewForAdd(hostEmail));
            LocalDate startDate = view.getStartDate();
            LocalDate endDate = view.getEndDate(startDate);
            Reservation reservation = view.makeReservation(guestEmail, hostEmail, endDate, startDate);

            Result<Reservation> result = reservationService.add(reservation);
             if (!result.isSuccessTwo()) {
                view.displayCancellation(false, result.getAddMessage());
            }
             if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else if (result.isSuccess() && result.isSuccessTwo()) {
                String successMessage = String.format("Reservation created", result.getPayload().getGuest().getTotal());
                view.displayStatus(true, successMessage);
            }
        } else {
            view.hostAndGuestNotInFile(hostEmail, guestEmail);
        }
        view.enterToContinue();
    }

    private void editReservation() throws DataException {
        view.displayHeader(MainMenuOption.EDIT_RESERVATION.getMessage());


        view.enterToContinue();
    }

    private void cancelReservation() throws DataException {
        view.displayHeader(MainMenuOption.CANCEL_RESERVATION.getMessage());


        view.enterToContinue();
    }
}
