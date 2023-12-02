package menu.exception;

public class DuplicateUnEatableFoodException extends IllegalArgumentException {

    public DuplicateUnEatableFoodException() {
        super("[ERROR] 못 먹는 메뉴에 중복된 메뉴가 있습니다.");
    }
}
