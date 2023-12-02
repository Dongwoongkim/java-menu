package menu.exception;

public class InvalidCoachNameSizeException extends IllegalArgumentException{

    public InvalidCoachNameSizeException(Integer minLength, Integer maxLength) {
        super(String.format("[ERROR] 코치 이름의 길이는 %d ~ %d 이어야 합니다.", minLength, maxLength));
    }
}
