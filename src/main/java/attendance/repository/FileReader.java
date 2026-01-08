package attendance.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReader {
    public List<AttendanceLog> readAttendanceLog() {
        File file = new File("src/main/resources/attendances.csv");
        if (!file.exists()) {
            throw new IllegalArgumentException("attendances.scv 파일이 없습니다.");
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            List<AttendanceLog> attendanceLogs = reader.lines()
                    .skip(1) // 헤더 스킵
                    .filter(line -> !line.isBlank()) // 빈줄 건너뛰기
                    .map(line -> line.split(","))
                    .map(row ->
                            new AttendanceLog(
                                    row[0].trim(), // trim()을 반드시 해줘야한다.!!!!!!!!!!!
                                    row[1].trim()))
                    .toList();
            return attendanceLogs;

        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는 과정에서 오류가 발생했습니다.");
        }
    }
}
