package attendance.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

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
        return date.equals(day);
    }

    public String getMessageForModify() {
        DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("HH:mm");
        String timeFormat = time.format(timeFormatter1);
        return String.format("%s (%s)", timeFormat, state.getState());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attendance that = (Attendance) o;
        return Objects.equals(name, that.name) && Objects.equals(date, that.date)
                && Objects.equals(time, that.time) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, time, state);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
        DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("HH:mm");
        String monthDayFormat = date.format(dateFormatter); // 09월 01일
        String dayOfKorea = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
        String timeFormat = time.format(timeFormatter1); // 08:04

        // 12월 13일 금요일 09:59 (출석, 지각, 결석)
        return String.format("%s %s %s (%s)", monthDayFormat, dayOfKorea, timeFormat, state.getState());
    }
}
