package menu.util;

import menu.exception.EmptyCoachNameException;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateCoachNames(String coachNames) {
        if (coachNames.isEmpty()) {
            throw new EmptyCoachNameException();
        }
    }
}
