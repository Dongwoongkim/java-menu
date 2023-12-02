package menu.exception;

public class DuplicateCoachNameException extends IllegalArgumentException {

    public DuplicateCoachNameException() {
        super("[ERROR] 코치 이름에는 중복이 허용되지 않습니다.");
    }
}
