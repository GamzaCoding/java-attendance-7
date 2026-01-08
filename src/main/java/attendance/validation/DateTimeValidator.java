package attendance.validation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Pattern;
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

    private static void validateCanAttendTime(String time) {
        LocalTime attendTime = LocalTime.parse(time);
        LocalTime start = LocalTime.of(8, 00);
        LocalTime end = LocalTime.of(23, 00);

        if (attendTime.isBefore(start) || attendTime.isAfter(end)) {
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
    }

    private static void validateFormat(String time) {
        Pattern HH_MM_24H = Pattern.compile("^(?:[01]\\d|2[0-3]):[0-5]\\d$");
        if (!HH_MM_24H.matcher(time).matches()) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }
}
