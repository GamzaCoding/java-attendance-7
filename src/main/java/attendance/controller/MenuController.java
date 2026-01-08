package attendance.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import view.InputView;
import view.OutputView;

public class MenuController {
    private static final String MENU_1 = "1";
    private static final String MENU_2 = "2";
    private static final String MENU_3 = "3";
    private static final String MENU_4 = "4";
    private static final String QUIT = "Q";
    private static final AttendanceController attendanceController = new AttendanceController();

    public void selectMenu() {
        while (true) {
            String menu = initMenuCommand();
            if (menu.equals(MENU_1)) {
//                attendanceController.startXXX_1_Logic();
            }
            if (menu.equals(MENU_2)) {
//                attendanceController.startXXX_2_Logic();
            }
            if (menu.equals(MENU_3)) {
//                attendanceController.startXXX_3_Logic();
            }
            if (menu.equals(MENU_4)) {
//                attendanceController.startXXX_3_Logic();
            }
            if (menu.equals(QUIT)) {
                return;
            }
        }
    }

    private String initMenuCommand() {
        LocalDate localDate = DateTimes.now().toLocalDate();
        LocalDate today = localDate.withYear(2024).withMonth(12).withDayOfMonth(13);

        try {
            OutputView.printStartMessage(today);
            String command = InputView.readMenuCommand();
//            validateInvalidCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            throw new IllegalArgumentException();
        }
    }
}
