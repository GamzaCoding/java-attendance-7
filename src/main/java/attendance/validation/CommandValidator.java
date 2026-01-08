package attendance.validation;

import java.util.List;

public class CommandValidator {
    public static void validateInvalidCommand(String command) {
        List<String> validCommands = List.of("1", "2", "3", "4", "Q");
        if (!validCommands.contains(command)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }
}
