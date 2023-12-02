package menu.exception;

public class EmptyCoachNameException extends IllegalArgumentException {

    public EmptyCoachNameException() {
        super("[ERROR] 코치 이름 리스트에 빈 값은 허용되지 않습니다.");
    }
}
