package view;

import attendance.repository.Attendance;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStartMessage(LocalDate today) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
        String monthDay = today.format(dateFormatter); // 09월 01일
        String dayOfKorea = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);

        String todayMessage = String.format("오늘은 %s %s입니다. 기능을 선택해 주세요.",monthDay, dayOfKorea);
        String message = """
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료""";

        System.out.println(todayMessage);
        System.out.println(message);
    }

    public static void printAttendanceMessage(Attendance attendance) {
        System.out.println();
        System.out.println(attendance);
        System.out.println();
    }

    public static void printInputNameMessage() {
        System.out.println();
        System.out.println("닉네임을 입력해 주세요.");
    }

    public static void printInputAttendTimeMessage() {
        System.out.println("등교 시간을 입력해 주세요.");
    }
}
