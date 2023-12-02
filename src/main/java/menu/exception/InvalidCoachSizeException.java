package menu.exception;

public class InvalidCoachSizeException extends IllegalArgumentException {

    public InvalidCoachSizeException(Integer minCoachSize, Integer maxCoachSize) {
        super(String.format("[ERROR] 코치 수의 범위는 %d명 ~ %d명 입니다.", minCoachSize, maxCoachSize));
    }
}
