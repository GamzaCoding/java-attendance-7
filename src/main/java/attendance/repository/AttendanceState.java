package attendance.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import model.DecCalendar;

public enum AttendanceState {
    ATTENDANCE("출석"),
    LATE("지각"),
    ABSENCE("결석")
    ;

    private final String state;
    AttendanceState(String state) {
        this.state = state;
    }

    public static AttendanceState calculateState(LocalDate date, LocalTime time) {
        if (DecCalendar.isMonday(date)) {
           return calculateMondayVer(time);
        }
        return calculateOtherDayVer(time);
    }

    private static AttendanceState calculateMondayVer(LocalTime time) {
        LocalTime successTime = LocalTime.of(13,5);
        LocalTime absenceTime = LocalTime.of(13,31);

        if (time.isBefore(successTime) || time.equals(successTime)) {
            return ATTENDANCE;
        }
        if (time.isAfter(successTime) && time.isBefore(absenceTime)) {
            return LATE;
        }
        return ABSENCE;
    }

    private static AttendanceState calculateOtherDayVer(LocalTime time) {
        LocalTime successTime = LocalTime.of(10,5);
        LocalTime absenceTime = LocalTime.of(10,31);

        if (time.isBefore(successTime) || time.equals(successTime)) {
            return ATTENDANCE;
        }
        if (time.isAfter(successTime) && time.isBefore(absenceTime)) {
            return LATE;
        }
        return ABSENCE;
    }

    public String getState() {
        return state;
    }
}
