package attendance.controller;

import attendance.validation.Validator;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import view.InputView;

public class AttendanceController {

    public void tryAttendance() {
        String name = inputName();
        LocalTime time = inputTime();
        LocalDate today = DateTimes.now().toLocalDate();




    }

    private String inputName() {
        String name = InputView.readName();
        Validator.validateValidName(name);
        return name;
    }

    private LocalTime inputTime() {
        String time = InputView.readTime();
        Validator.validateValidAttendTime(time);
        return LocalTime.parse(time);
    }
}
