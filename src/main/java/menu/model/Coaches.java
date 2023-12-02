package menu.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Coaches {

    private static final Integer MIN_COACH_SIZE = 2;
    private static final Integer MAX_COACH_SIZE = 5;

    private final List<Coach> coaches;

    private Coaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public static Coaches createByNames(List<String> coachNames) {
        validate(coachNames);
        return new Coaches(coachNames.stream()
                .map(coachName -> Coach.createByName(coachName))
                .collect(Collectors.toUnmodifiableList()));
    }

    private static void validate(List<String> coachNames) {
        if (coachNames.size() < MIN_COACH_SIZE || coachNames.size() > MAX_COACH_SIZE) {
            throw new IllegalArgumentException();
        }
        if (isContainDuplicateName(coachNames)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isContainDuplicateName(List<String> coachNames) {
        Set<String> uniqueNames = new HashSet<>(coachNames);
        return uniqueNames.size() != coachNames.size();
    }
}
