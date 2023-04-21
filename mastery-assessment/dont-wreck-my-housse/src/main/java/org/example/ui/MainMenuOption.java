package org.example.ui;

public enum MainMenuOption {
    EXIT(0, "Exit"),
    VIEW_RESERVATIONS_FOR_HOST(1, "View Reservations for Host"),
    MAKE_RESERVATION(2, "Make a Reservation"),
    EDIT_RESERVATION(3, "Edit a Reservation"),
    CREATE_GUEST(4, "Create a Guest"),
    CREATE_HOST(5, "Create a Host"),
    CANCEL_RESERVATION(6, "Cancel a Reservation");
    private int value;
    private String message;

    MainMenuOption(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption option : MainMenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
