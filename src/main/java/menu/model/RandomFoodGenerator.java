package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomFoodGenerator {

    public String pickRandomFood(List<String> foods) {
        return Randoms.shuffle(foods).get(0);
    }
}
