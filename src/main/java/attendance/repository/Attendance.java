package attendance.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Attendance {
    private final String name;
    private final LocalDate date;
    private final LocalTime time;
    private final AttendanceState state;

    // 이름, 날짜, 시간을 받으면 자동으로 출석,지각,결석을 계산한다.
    public Attendance(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.state = AttendanceState.calculateState(date, time);
    }

    public String getName() {
        return name;
    }

    public boolean isSameDate(LocalDate day) {
        return day == date;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
        DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("HH:mm");
        String monthDayFormat = date.format(dateFormatter); // 09월 01일
        String dayOfKorea = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
        String timeFormat = time.format(timeFormatter1); // 08:04

        return String.format("%s %s %s (%s)", monthDayFormat, dayOfKorea, timeFormat, state.getState());
    }
}
