package model;

import java.time.LocalDate;
import java.util.List;

public class DecCalendar {
    private final static List<Integer> mondayNum = List.of(2,9,16,23,30);

    public static boolean isMonday(LocalDate date) {
        return mondayNum.contains(date.getDayOfMonth());
    }
}
