package menu.exception;

public class OverMaxUnEatableFoodNameSize extends IllegalArgumentException {

    public OverMaxUnEatableFoodNameSize(Integer maxUneatableFoodSize) {
        super(String.format("[ERROR] 못 먹는 메뉴의 최대 개수는 %d 입니다.", maxUneatableFoodSize));
    }
}
