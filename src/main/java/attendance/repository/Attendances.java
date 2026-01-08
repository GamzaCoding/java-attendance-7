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
}
