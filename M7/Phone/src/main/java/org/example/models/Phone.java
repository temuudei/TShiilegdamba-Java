package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.*;

public class Phone {

    private int phoneId;
    @NotBlank(message =  "Phone name cannot be empty.")
    private String phoneName;
    @NotBlank(message = "Operating system cannot be null.")
    private String phoneOS;
    @NotBlank(message = "Release date cannot be null.")
    @PastOrPresent(message = "Release date cannot be in the future.")
    private LocalDate releaseDate;
    @NotNull(message = "Phone price cannot be null.")
    private BigDecimal phonePrice;
    private boolean isStillInGoodCondition;

    public Phone(){}

    public Phone(int phoneId, String phoneName, String phoneOS, LocalDate releaseDate, BigDecimal phonePrice, boolean isStillInGoodCondition) {
        this.phoneId = phoneId;
        this.phoneName = phoneName;
        this.phoneOS = phoneOS;
        this.releaseDate = releaseDate;
        this.phonePrice = phonePrice;
        this.isStillInGoodCondition = isStillInGoodCondition;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneOS() {
        return phoneOS;
    }

    public void setPhoneOS(String phoneOS) {
        this.phoneOS = phoneOS;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(BigDecimal phonePrice) {
        this.phonePrice = phonePrice;
    }

    public boolean isStillInGoodCondition() {
        return isStillInGoodCondition;
    }

    public void setStillInGoodCondition(boolean stillInGoodCondition) {
        isStillInGoodCondition = stillInGoodCondition;
    }
}
