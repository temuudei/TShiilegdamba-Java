package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(7, 24, 34);
        Duration timeSinceMidnight = getTimeSinceMidnight(localTime);
        System.out.printf("%d:%d:%d", timeSinceMidnight.toHoursPart(), timeSinceMidnight.toMinutesPart(), timeSinceMidnight.toSecondsPart());
    }

    public static Duration getTimeSinceMidnight(LocalTime localTime) {
        LocalTime temp = LocalTime.of(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
        LocalTime midnightTime = LocalTime.MIDNIGHT;
        Duration difference = Duration.between(midnightTime, temp);
        return difference;
    }
}