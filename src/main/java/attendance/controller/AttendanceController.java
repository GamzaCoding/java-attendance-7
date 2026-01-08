package attendance.controller;

import attendance.validation.NameValidator;
import attendance.validation.DateTimeValidator;
import java.time.LocalDate;
import java.time.LocalTime;
import view.InputView;

public class AttendanceController {

    public void tryAttendance(LocalDate today) {
        DateTimeValidator.validateCanAttendDay(today);
        String name = inputName();
        LocalTime time = inputTime(today);

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
