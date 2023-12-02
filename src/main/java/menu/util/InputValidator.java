package menu.util;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateCoachNames(String coachNames) {
        if (coachNames.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
