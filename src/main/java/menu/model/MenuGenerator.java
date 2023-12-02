package menu.model;

import java.util.List;

public interface MenuGenerator {

    List<Integer> pickCategory();

    String pickRandomFood(List<String> foods);
}
