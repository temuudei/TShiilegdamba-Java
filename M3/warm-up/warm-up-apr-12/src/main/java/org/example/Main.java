package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static BigDecimal to_industrial(LocalTime time) {
        BigDecimal result = new BigDecimal(time.getHour());
        int minutes = time.getMinute();
        BigDecimal minuteDecimal = BigDecimal.valueOf(minutes / 60).setScale(2, RoundingMode.HALF_UP);
        return minuteDecimal;
    }

    public static BigDecimal to_industrial(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
        LocalTime time = LocalTime.parse(timeString, formatter);
        return to_industrial(time);
    }

    public static LocalTime to_time(BigDecimal time) {
        LocalTime result = LocalTime.MIDNIGHT;
        BigDecimal[] hourAndMinute = time.divideAndRemainder(BigDecimal.ONE);
        result = result.plusHours(hourAndMinute[0].longValue());
        long minutes = hourAndMinute[1].multiply(BigDecimal.valueOf(60)).longValue();
        result = result.plusMinutes(minutes);
        return result;
    }
}