package attendance.controller;

import attendance.repository.Attendance;
import attendance.repository.AttendanceRepository;
import attendance.validation.NameValidator;
import attendance.validation.DateTimeValidator;
import java.time.LocalDate;
import java.time.LocalTime;
import view.InputView;
import view.OutputView;

public class AttendanceController {
    private final AttendanceRepository repository = AttendanceRepository.getInstance();

    public void tryAttendance(LocalDate today) {
        DateTimeValidator.validateCanAttendDay(today);
        String name = inputName();
        LocalTime time = inputTime(today);
        repository.save(name, today, time);
        Attendance attendance = repository.getAttendanceByNameAndDay(name, today);
        OutputView.printAttendanceMessage(attendance);
    }

    private String inputName() {
        OutputView.printInputNameMessage();
        String name = InputView.readName();
        NameValidator.validateNameForAttend(name);
        return name;
    }

    private LocalTime inputTime(LocalDate today) {
        OutputView.printInputAttendTimeMessage();
        String time = InputView.readTime();
        DateTimeValidator.validateValidAttendTime(time);
        return LocalTime.parse(time);
    }
}
