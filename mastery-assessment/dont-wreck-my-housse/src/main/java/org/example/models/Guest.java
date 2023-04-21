package org.example.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Guest {
    private String firstName;
    private String lastName;
    private String email;
    private int guestID;
    private String phone;
    private String state;
    private BigDecimal total;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return guestID == guest.guestID && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && Objects.equals(email, guest.email) && Objects.equals(phone, guest.phone) && Objects.equals(state, guest.state) && Objects.equals(total, guest.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, guestID, phone, state, total);
    }
}
