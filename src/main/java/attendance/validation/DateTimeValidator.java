package attendance.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import model.DecCalendar;

public class DateTimeValidator {
    public static void validateCanAttendDay(LocalDate day) {
        if (DecCalendar.isHoliday(day)) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
            String monthDay = day.format(dateFormatter); // 09월 01일
            String dayOfKorea = day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);

            String message = String.format("[ERROR] %s %s은 등교일이 아닙니다.", monthDay, dayOfKorea);
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateValidAttendTime(String time) {
        validateFormat(time);
        validateCanAttendTime(time);
    }
}
