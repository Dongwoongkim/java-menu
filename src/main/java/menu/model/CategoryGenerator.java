package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryGenerator {

    public List<Integer> pickCategory() {
        List<Integer> categories = new ArrayList<>();

        while (categories.size() < 5) {
            int pickNumber = Randoms.pickNumberInRange(1, 5);
            int frequency = Collections.frequency(categories, pickNumber);

            if (frequency < 2) {
                categories.add(pickNumber);
            }
        }
        return categories;
    }
}
