package learning.alien.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Alien {
    private int alienId;
    private String alienName;
    private BigDecimal alienPrice;
    private LocalDate alienDate;

    public int getAlienId() {

        return alienId;
    }

    public void setAlienId(int alienId) {
        this.alienId = alienId;
    }

    public String getAlienName() {
        return alienName;
    }

    public void setAlienName(String alienName) {
        this.alienName = alienName;
    }

    public BigDecimal getAlienPrice() {
        return alienPrice;
    }

    public void setAlienPrice(BigDecimal alienPrice) {
        this.alienPrice = alienPrice;
    }

    public LocalDate getAlienDate() {
        return alienDate;
    }

    public void setAlienDate(LocalDate alienDate) {
        this.alienDate = alienDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alien alien = (Alien) o;
        return alienId == alien.alienId && Objects.equals(alienName, alien.alienName) && Objects.equals(alienPrice, alien.alienPrice) && Objects.equals(alienDate, alien.alienDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alienId, alienName, alienPrice, alienDate);
    }

    @Override
    public String toString() {
        return "Alien{" +
                "alienId=" + alienId +
                ", alienName='" + alienName + '\'' +
                ", alienPrice=" + alienPrice +
                ", alienDate=" + alienDate +
                '}';
    }
}
