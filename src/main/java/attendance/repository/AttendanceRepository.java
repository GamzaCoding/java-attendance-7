package attendance.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceRepository {
    // 출석표, 이름: 해당 크루의 출석정보들
    private final static Map<String, Attendances> attendanceSheet = new HashMap<>();

    private final static AttendanceRepository attendanceRepository = new AttendanceRepository();

    private AttendanceRepository(){
        initAttendanceSheetFromFile();
    }

    public void removeAttendance(String name, Attendance oldAttendance) {
        Attendances attendances = getAttendancesByName(name);
        attendances.remove(oldAttendance);
    }

    public void save(String name, LocalDate date, LocalTime time) {
        if (attendanceSheet.containsKey(name)) {
            Attendances attendances = attendanceSheet.get(name);
            attendances.addAttendance(new Attendance(name, date, time));
        }
    }

    public void save(String name, Attendance attendance) {
        if (attendanceSheet.containsKey(name)) {
            Attendances attendances = attendanceSheet.get(name);
            attendances.addAttendance(attendance);
        }
    }
    public Attendance getAttendanceByNameAndDay(String name, LocalDate today) {
        Attendances attendances = getAttendancesByName(name);
        return attendances.findAttendance(today);
    }

    public Attendances getAttendancesByName(String name) {
        return attendanceSheet.getOrDefault(name, null); // 이건 나중에 고치자
    }

    private static void initAttendanceSheetFromFile() {
        FileReader fileReader = new FileReader();
        List<AttendanceLog> attendanceLogs = fileReader.readAttendanceLog();
        saveInitNames(attendanceLogs);
        saveAttendanceFromLog(attendanceLogs);
    }

    private static void saveAttendanceFromLog(List<AttendanceLog> attendanceLogs) {
        List<Attendance> attendances = attendanceLogs.stream()
                .map(log -> new Attendance(log.name(), log.date(), log.time()))
                .toList();

        attendances.forEach(attendance -> saveForInit(attendance.getName(), attendance));

    }

    private static void saveForInit(String name, Attendance attendance) {
        if (attendanceSheet.containsKey(name)) {
            Attendances attendances = attendanceSheet.get(name);
            attendances.addAttendance(attendance);
        }
    }

    private static void saveInitNames(List<AttendanceLog> attendanceLogs) {
        List<String> names = attendanceLogs.stream()
                .map(AttendanceLog::name)
                .distinct()
                .toList();
        names.forEach(name -> attendanceSheet.put(name, new Attendances()));
    }

    public static AttendanceRepository getInstance() {
        return attendanceRepository;
    }

    public boolean isContain(String name) {
        return attendanceSheet.containsKey(name);
    }
}
