package menu.exception;

public class NonContainInMenuException extends IllegalArgumentException {

    public NonContainInMenuException() {
        super("[ERROR] 메뉴판에 속하지 않은 메뉴입니다.");
    }
}
