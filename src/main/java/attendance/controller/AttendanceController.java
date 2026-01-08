package attendance.controller;

import attendance.repository.AttendanceRepository;
import attendance.validation.NameValidator;
import attendance.validation.DateTimeValidator;
import java.time.LocalDate;
import java.time.LocalTime;
import view.InputView;

public class AttendanceController {
    private final AttendanceRepository repository = AttendanceRepository.getInstance();

    public void tryAttendance(LocalDate today) {
        DateTimeValidator.validateCanAttendDay(today);
        String name = inputName();
        LocalTime time = inputTime(today);
        repository.save(name, today, time);
    }

    private String inputName() {
        String name = InputView.readName();
        NameValidator.validateValidName(name);
        return name;
    }

    private LocalTime inputTime(LocalDate today) {
        String time = InputView.readTime();
        DateTimeValidator.validateValidAttendTime(time);
        return LocalTime.parse(time);
    }
}
