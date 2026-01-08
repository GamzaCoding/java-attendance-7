package attendance.controller;

import attendance.repository.Attendance;
import attendance.repository.AttendanceRepository;
import attendance.validation.DateTimeValidator;
import attendance.validation.NameValidator;
import java.time.LocalDate;
import java.time.LocalTime;
import view.InputView;
import view.OutputView;

public class ModifyController {
    private AttendanceRepository repository = AttendanceRepository.getInstance();

    public void modifyAttendance() {
        String name = inputName();
        LocalDate date = inputDate();
        LocalTime time = inputTime();
        Attendance oldAttendance = repository.getAttendanceByNameAndDay(name, date);

        repository.removeAttendance(name, oldAttendance);
        Attendance newAttendance = new Attendance(name, date, time);
        OutputView.printModifyAttendanceMessage(oldAttendance, newAttendance);
        repository.save(name, newAttendance);
    }

    private LocalTime inputTime() {
        OutputView.printModifyTimeMessage();
        String time = InputView.readTime();
        DateTimeValidator.validateValidAttendTime(time);
        return LocalTime.parse(time);
    }

    private LocalDate inputDate() {
        OutputView.printModifyDateMessage();
        String day = InputView.readDate();
        int date = Integer.parseInt(day);
        DateTimeValidator.validateValidDate(date);
        return LocalDate.of(2024,12, date);
    }

    private String inputName() {
        OutputView.printModifyNameMessage();
        String name = InputView.readName();
        NameValidator.validateValidName(name);
        return name;
    }
}
