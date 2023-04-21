package org.example.ui;

import org.example.data.DataException;
import org.example.domain.ReservationService;
import org.example.domain.Result;
import org.example.models.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Controller {
    private final ReservationService reservationService;
    private final View view;

    public Controller(ReservationService reservationService, View view) {
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
                case VIEW_RESERVATIONS_FOR_HOST -> viewReservations();
                case MAKE_RESERVATION -> makeReservation();
                case EDIT_RESERVATION -> editReservation();
                case CANCEL_RESERVATION -> cancelReservation();
            }
        } while (option != MainMenuOption.EXIT);
    }

    //Displays reservations
    private void viewReservations() {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_FOR_HOST.getMessage());
        String email = view.getHostEmail();
        List<Reservation> reservations = reservationService.view(email);
        if (reservations != null) {
            view.displayHeader(view.showHostInfo(reservationService.viewHostInfo(email)));
        }
        view.displayReservations(reservations);
        view.enterToContinue();
    }

    //Makes a reservations
    private void makeReservation() throws DataException {
        view.displayHeader(MainMenuOption.MAKE_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        String hostEmail = view.getHostEmail();

        if (reservationService.validateHostInTheFile(hostEmail) && reservationService.validateGuestInTheFile(guestEmail)) {
            view.displayHeader(view.showHostInfo(reservationService.viewHostInfo(hostEmail)));
            view.displayFutureReservations(reservationService.viewForAdd(hostEmail));
            LocalDate startDate = view.getStartDate();
            LocalDate endDate = view.getEndDate(startDate);
            Reservation reservation = view.makeReservation(guestEmail, hostEmail, endDate, startDate);

            Result<Reservation> result = reservationService.sendAddReservationInfo(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                if (view.showSummary(result.getPayload().getStartDate(), result.getPayload().getEndDate(), result.getPayload().getGuest().getTotal())) {
                    reservationService.add(reservation);
                    String successMessage = String.format("Reservation %d added", result.getPayload().getId());
                    view.displayStatus(true, successMessage);
                } else {
                    String notSuccessMessage = "Reservation was not added";
                    view.displayCancellation(false, notSuccessMessage);
                }
            }
        } else {
            view.getHostAndGuestNotInFile(hostEmail, guestEmail);
        }
        view.enterToContinue();
    }

    //Edits reservations
    private void editReservation() throws DataException {
        view.displayHeader(MainMenuOption.EDIT_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        String hostEmail = view.getHostEmail();
        if (reservationService.validateHostInTheFile(hostEmail) && reservationService.validateGuestInTheFile(guestEmail)) {
            List<Reservation> updatingReservation = reservationService.showIfGuestInHostReservation(view.makeReservation(guestEmail, hostEmail));

            if (updatingReservation.size() >= 1) {
                view.displayHeader(view.showHostInfo(reservationService.viewHostInfo(hostEmail)));
                view.displayReservations(updatingReservation);
                int reservationID;
                if (updatingReservation.size() >= 2) {
                    do {
                        reservationID = view.getReservationID();
                    } while (!view.validateIdInTheFile(updatingReservation, reservationID));
                } else {
                    reservationID = updatingReservation.get(0).getId();
                }
                view.displayEditingID(reservationID);
                LocalDate startDate = view.getStartDate();
                LocalDate endDate = view.getEndDate(startDate);
                Reservation reservation = view.makeReservation(guestEmail, hostEmail, endDate, startDate, reservationID);
                Result<Reservation> result = reservationService.sendUpdateReservationInfo(reservation);

                if (!result.isSuccess()) {
                    view.displayStatus(false, result.getErrorMessages());
                } else {
                    if (view.showSummary(result.getPayload().getStartDate(), result.getPayload().getEndDate(), result.getPayload().getGuest().getTotal())) {
                        if (reservationService.update(reservation)) {
                            String successMessage = String.format("Reservation %d updated", result.getPayload().getId());
                            view.displayStatus(true, successMessage);
                        }
                    } else {
                        String notSuccessMessage = String.format("Reservation %d was not updated", result.getPayload().getId());
                        view.displayCancellation(false, notSuccessMessage);
                    }
                }
            } else {
                view.getHostAndGuestNoReservation(hostEmail, guestEmail);
            }
        } else {
            view.getHostAndGuestNotInFile(hostEmail, guestEmail);
        }
        view.enterToContinue();
    }

    //Deletes a reservation
    private void cancelReservation() throws DataException {
        view.displayHeader(MainMenuOption.CANCEL_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        String hostEmail = view.getHostEmail();
        if (reservationService.validateHostInTheFile(hostEmail) && reservationService.validateGuestInTheFile(guestEmail)) {
            List<Reservation> updatingReservation = reservationService.showIfGuestInHostReservation(view.makeReservation(guestEmail, hostEmail));

            if (updatingReservation.size() >= 1) {
                view.displayHeader(view.showHostInfo(reservationService.viewHostInfo(hostEmail)));
                view.displayFutureReservations(updatingReservation);
                int reservationID;
                if (updatingReservation.size() >= 2) {
                    do {
                        reservationID = view.getReservationIDForDelete();
                    } while (!view.validateIdInTheFile(updatingReservation, reservationID));
                } else {
                    reservationID = updatingReservation.get(0).getId();
                }

                Result<Reservation> result = reservationService.delete(view.makeReservation(guestEmail, hostEmail, reservationID));
                if (!result.isSuccess()) {
                    view.displayStatus(false, result.getErrorMessages());
                } else {
                    String successMessage = String.format("Reservation %d deleted", result.getPayload().getId());
                    view.displayStatus(true, successMessage);
                }
            } else {
                view.getHostAndGuestNoReservation(hostEmail, guestEmail);
            }
        } else {
            view.getHostAndGuestNotInFile(hostEmail, guestEmail);
        }
        view.enterToContinue();
    }
}
