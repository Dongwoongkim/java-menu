package menu.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {

    private static final String DELIMITER = ",";

    private InputConverter() {
    }

    public static List<String> convertStringToStringListByDelimiter(String coachNames) {
        return Arrays.stream(coachNames.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }
}
