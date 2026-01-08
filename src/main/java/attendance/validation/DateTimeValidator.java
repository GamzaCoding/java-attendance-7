package attendance.validation;

import camp.nextstep.edu.missionutils.DateTimes;
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

    public static void validateValidDate(int date) {
        validateValidDayRange(date);
        validateFuture(date);
        validateHoliday(date);
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

    private static void validateHoliday(int date) {
        LocalDate targetDay = LocalDate.of(2024, 12, date);

        if (DecCalendar.isHoliday(targetDay)) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
            String monthDay = targetDay.format(dateFormatter); // 09월 01일
            String dayOfKorea = targetDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);

            String message = String.format("[ERROR] %s %s은 등교일이 아닙니다.", monthDay, dayOfKorea);
            throw new IllegalArgumentException(message);
        }
    }

    private static void validateFuture(int date) {
        LocalDate today = DateTimes.now().toLocalDate();

        if (date > today.getDayOfMonth()) {
            throw new IllegalArgumentException("[ERROR] 아직 수정할 수 없습니다.");
        }
    }

    private static void validateValidDayRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }
}
