package menu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomMenuGeneratorTest {

    private static final Integer FIRST_INDEX = 0;
    private static final Integer FIRST_CATEGORY_NUMBER = 1;
    private static final Integer LAST_CATEGORY_NUMBER = 5;
    private static final Integer CATEGORY_MAX_SIZE = 5;
    private static final Integer MAX_FREQUENCY = 2;


    @DisplayName("카테고리의 번호를 랜덤으로 고르는 기능 테스트")
    @Test
    void testPickCategory() {
        // given
        RandomMenuGenerator menuGenerator = new RandomMenuGenerator();

        // when
        List<Integer> categories = menuGenerator.pickCategory();

        // then
        assertEquals(CATEGORY_MAX_SIZE, categories.size());
        assertTrue(categories.stream().allMatch(num -> num >= FIRST_CATEGORY_NUMBER && num <= LAST_CATEGORY_NUMBER));
    }

    @DisplayName("랜덤한 음식을 고르는 기능 테스트")
    @Test
    void testPickRandomFood() {
        // given
        RandomMenuGenerator menuGenerator = new RandomMenuGenerator();

        // when
        List<String> foods = Arrays.asList("Pizza", "Burger", "Sushi", "Pasta", "Salad");
        String randomFood = menuGenerator.pickRandomFood(foods);

        // then
        assertTrue(foods.contains(randomFood));
    }
}
