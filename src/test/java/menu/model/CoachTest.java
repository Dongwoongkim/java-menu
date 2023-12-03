package menu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import menu.exception.DuplicateUnEatableFoodException;
import menu.exception.OverMaxUnEatableFoodNameSize;
import menu.model.vo.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoachTest {

    Coach coach;
    MenuGenerator menuGenerator;


    @BeforeEach
    void init() {
        coach = Coach.createByName("kdo");

        menuGenerator = new MenuGenerator() {
            @Override
            public List<Integer> pickCategory() {
                return List.of(1, 1, 1, 1, 1);
            }

            @Override
            public String pickRandomFood(List<String> foods) {
                return "김치찌개";
            }
        };
    }

    @DisplayName("팩토리 메소드 생성자 테스트")
    @Test
    void testCreateCoach() {
        assertEquals("kdo", coach.getName());
    }

    @DisplayName("먹지 못하는 메뉴 추가 테스트")
    @Test
    void testAddUneatableFood() {
        // when
        List<String> uneatableFoods = Arrays.asList("우동", "스시");
        coach.addUnEatableFood(uneatableFoods);

        // then;
        assertEquals(coach.getUnEatableFoods().size(), 2);
        assertEquals(coach.getUnEatableFoods().get(0), new Food("우동"));
        assertEquals(coach.getUnEatableFoods().get(1), new Food("스시"));
    }

    @DisplayName("먹지 못하는 메뉴 리스트에 중복된 값이 있으면 예외를 발생해야 한다.")
    @Test
    void testAddDuplicateUneatableFood() {
        // when
        List<String> duplicateUneatableFoods = Arrays.asList("우동", "우동");

        // then
        assertThrows(DuplicateUnEatableFoodException.class, () -> coach.addUnEatableFood(duplicateUneatableFoods));
    }

    @DisplayName("먹지 못하는 음식이 3개 이상인 경우 예외를 발생해야 한다.")
    @Test
    void testAddTooManyUneatableFoods() {
        // when
        List<String> tooManyUneatableFoods = Arrays.asList("Pizza", "Burger", "Sushi");

        // then
        assertThrows(OverMaxUnEatableFoodNameSize.class, () -> coach.addUnEatableFood(tooManyUneatableFoods));
    }

    @DisplayName("하루의 점심 메뉴를 추천해주는 메소드 기능 테스트")
    @Test
    void testMakeOneDayPlan() {
        // when
        List<String> uneatableFoods = Arrays.asList("우동", "스시");
        coach.addUnEatableFood(uneatableFoods);
        coach.makeOneDayPlan(menuGenerator.pickCategory().get(0), menuGenerator);
        List<String> plan = coach.getPlan();

        // then
        assertFalse(plan.isEmpty());
        assertEquals(plan.get(0), "김치찌개");
    }
}
