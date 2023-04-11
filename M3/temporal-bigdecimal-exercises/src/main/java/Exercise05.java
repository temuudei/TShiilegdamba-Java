import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Exercise05 {

    // THE GODMOTHER
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. Your Godmother gives you $10 every other Friday throughout the year.
    // Payments start on the first Friday of the year.
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateGiftsTilEndOfYear(LocalDate date) {
        long daysLeft = date.until(date.withDayOfYear(date.lengthOfYear()), ChronoUnit.DAYS);
        DayOfWeek day = date.getDayOfWeek();
        LocalDate temp = date;
        while (temp.getDayOfWeek() != DayOfWeek.FRIDAY) {
            temp = temp.plusDays(1);
            daysLeft--;
        }
        int gifts = 1;
        for (long i = daysLeft; i >= 14; i -= 14) {
            gifts++;
        }
        return new BigDecimal(gifts * 10);
    }

    // 2. Your Godmother is getting quirky. She adjusted her payment schedule.
    // She still pays every other Friday throughout the year, starting on the first Friday of the year.
    // But now, she pays a number of dollars equal to the day of the month.
    // Examples
    // Jan 31 == $31
    // Mar 1 == $1
    // July 12 == $12
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateQuirkyGiftsTilEndOfYear(LocalDate date) {
        return null;
    }

}
