package menu.model.vo;

import menu.exception.InvalidCoachNameSizeException;

public class Name {

    private final Integer MIN_LENGTH = 2;
    private final Integer MAX_LENGTH = 4;

    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!isLengthValid(name)) {
            throw new InvalidCoachNameSizeException(MIN_LENGTH, MAX_LENGTH);
        }
    }

    private boolean isLengthValid(String name) {
        if (name.length() >= MIN_LENGTH && name.length() <= MAX_LENGTH) {
            return true;
        }
        return false;
    }
}
