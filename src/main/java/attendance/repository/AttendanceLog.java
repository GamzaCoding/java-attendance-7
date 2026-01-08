package attendance.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record AttendanceLog(String name, String dateTime) {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String name() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(dateTime, formatter);
    }

    public LocalDate date() {
        return LocalDateTime.parse(dateTime, formatter).toLocalDate();
    }

    public LocalTime time() {
        return LocalDateTime.parse(dateTime, formatter).toLocalTime();
    }
}
