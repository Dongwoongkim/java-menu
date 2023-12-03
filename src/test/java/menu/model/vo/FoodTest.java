package menu.model.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import menu.exception.NonContainInMenuException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTest {


    @DisplayName("제시한 메뉴에 존재하지 않는 이름으로 생성 시 예외가 발생한다")
    @Test
    void testInvalidFoodCreation() {
        assertThrows(NonContainInMenuException.class, () -> new Food("NonExistentFood"));
    }

    @DisplayName("음식의 이름에 대한 필드값이 동일한 경우 두 객체는 동일한 객체로 취급해야 한다.")
    @Test
    void testEqualsAndHashCode() {
        // when
        Food food1 = new Food("스시");
        Food food2 = new Food("스시");
        Food food3 = new Food("우동");

        // then
        assertEquals(food1, food2);
        assertNotEquals(food1, food3);
        assertEquals(food1.hashCode(), food2.hashCode());
        assertNotEquals(food1.hashCode(), food3.hashCode());
    }
}
