package attendance.controller;

import attendance.dto.AttendanceRecord;
import attendance.repository.AttendanceRepository;
import attendance.repository.Attendances;
import attendance.validation.NameValidator;
import view.InputView;
import view.OutputView;

public class LookUpController {
    private AttendanceRepository repository = AttendanceRepository.getInstance();

    public void lookUp() {
        String name = inputName();
        AttendanceRecord attendanceRecord = calculateAttendanceOf(name);
        OutputView.printAttendanceRecord(name, attendanceRecord);
    }

    private AttendanceRecord calculateAttendanceOf(String name) {
        Attendances attendances = repository.getAttendancesByName(name);
        return new AttendanceRecord(attendances);
    }

    private String inputName() {
        OutputView.printInputNameMessage();
        String name = InputView.readName();
        NameValidator.validateValidName(name);
        return name;
    }
}
