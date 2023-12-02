package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomMenuGenerator implements MenuGenerator {

    private static final Integer FIRST_INDEX = 0;

    private static final Integer FIRST_CATEGORY_NUMBER = 1;
    private static final Integer LAST_CATEGORY_NUMBER = 5;
    private static final Integer CATEGORY_MAX_SIZE = 5;
    private static final Integer MAX_FREQUENCY = 2;

    public List<Integer> pickCategory() {
        List<Integer> categories = new ArrayList<>();
        while (categories.size() < CATEGORY_MAX_SIZE) {
            int pickNumber = Randoms.pickNumberInRange(FIRST_CATEGORY_NUMBER, LAST_CATEGORY_NUMBER);
            int frequency = Collections.frequency(categories, pickNumber);
            validateFrequency(categories, pickNumber, frequency);
        }
        return categories;
    }

    private void validateFrequency(List<Integer> categories, int pickNumber, int frequency) {
        if (frequency < MAX_FREQUENCY) {
            categories.add(pickNumber);
        }
    }

    public String pickRandomFood(List<String> foods) {
        return Randoms.shuffle(foods).get(FIRST_INDEX);
    }
}
