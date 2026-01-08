package attendance.dto;

import attendance.repository.Attendance;
import attendance.repository.Attendances;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.DecCalendar;

public class AttendanceRecord {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일");

    private final Attendances attendances;
    private final List<String> records;
    private int attendance = 0;
    private int late = 0;
    private int absence = 0;

    public AttendanceRecord(Attendances attendances) {
        this.attendances = attendances;
        this.records = initAttendanceRecords();
        initTotalState();
    }

    public List<String> getRecords() {
        return records;
    }

    public String getStates() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("출석: %d회", attendance)).append("\n")
                .append(String.format("지각: %d회", late)).append("\n")
                .append(String.format("결석: %d회", absence)).append("\n");
        return sb.toString();
    }

    public String calculateState() {
        int judgment = absence + (late / 3);
        if (judgment == 2) {
            return "경고 대상자입니다.";
        }
        if (judgment >= 3 && judgment <= 5) {
            return "면담 대상자입니다.";
        }
        if (judgment > 5) {
            return "제적 대상자입니다.";
        }

        return "정상입니다.";
    }


    private void initTotalState() {
        for (String record : records) {
            if (record.contains("출석")) {
                attendance++;
            }
            if (record.contains("지각")) {
                late++;
            }
            if (record.contains("결석")) {
                absence++;
            }
        }
    }

    private List<String> initAttendanceRecords() {
        List<String> records = new ArrayList<>();
        LocalDateTime now = DateTimes.now();
        int dayOfMonth = now.getDayOfMonth();
        dayOfMonth = 13;

        for (int dayIndex = 1; dayIndex < dayOfMonth; dayIndex++) {
            LocalDate targetDate = LocalDate.of(2024, 12, dayIndex);

            if (DecCalendar.isHoliday(targetDate)) {
                continue;
            }

            Attendance targetAttendance = attendances.getAttendances().stream()
                    .filter(attendance -> attendance.isSameDate(targetDate))
                    .findFirst()
                    .orElse(null);

            if (targetAttendance != null) {
                records.add(targetAttendance.toString());
                continue;
            }
            String monthDay = targetDate.format(dateFormatter); // 09월 01일
            String dayOfKorea = targetDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREA);
            String noData = String.format("%s %s --:-- (결석)", monthDay, dayOfKorea);
            records.add(noData);
        }
        return records;
    }
}
