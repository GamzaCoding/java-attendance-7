package model;

import java.time.LocalDate;
import java.util.List;

public class DecCalendar {
    private final static List<Integer> mondays = List.of(2,9,16,23,30);
    private final static List<Integer> holidays = List.of(1,7,8,14,15,21,22,25,28,29);

    public static boolean isMonday(LocalDate date) {
        return mondays.contains(date.getDayOfMonth());
    }

    public static boolean isHoliday(LocalDate day) {
        return holidays.contains(day.getDayOfMonth());
    }
}
