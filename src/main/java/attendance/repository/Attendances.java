package attendance.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Attendances {
    private final List<Attendance> attendances = new ArrayList<>();

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public boolean alreadyAttended(LocalDate day) {
        return attendances.stream()
                .anyMatch(attendance -> attendance.isSameDate(day));
    }

    public Attendance findAttendance(LocalDate day) {
        if (!hasAttendance(day)) {
            throw new IllegalArgumentException("[ERROR] 뭔가 잘못된 상황입니다");
        }
        return attendances.stream()
                .filter(attendance -> attendance.isSameDate(day))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 뭔가 잘못된 상황입니다"));
    }

    private boolean hasAttendance(LocalDate day) {
       return attendances.stream()
                .anyMatch(attendance -> attendance.isSameDate(day));
    }
}
