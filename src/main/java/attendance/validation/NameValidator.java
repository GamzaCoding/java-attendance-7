package attendance.validation;

import attendance.repository.AttendanceRepository;
import attendance.repository.Attendances;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NameValidator {
    public static void validateNameForAttend(String name, LocalDate today) {
        validateNameFormat(name);
        validateInRepository(name);
//        validateIsAlreadyAttend(name, today);
    }

    public static void validateValidName(String name) {
        validateNameFormat(name);
        validateInRepository(name);
    }

    private static void validateNameFormat(String name) {
        boolean hasDigit = name.matches(".*\\d.*");
        if (hasDigit) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    private static void validateInRepository(String name) {
        AttendanceRepository repository = AttendanceRepository.getInstance();
        if (!repository.isContain(name)) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }

    private static void validateIsAlreadyAttend(String name, LocalDate today) {
        AttendanceRepository repository = AttendanceRepository.getInstance();

        Attendances attendances = repository.getAttendancesByName(name);
        if (attendances.alreadyAttended(today)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
    }
}
