package menu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("카테고리의 번호로 음식 이름 리스트를 가져오는 기능 테스트")
    @Test
    void testGetFoodBySequence() {
        // when
        List<String> japaneseFood = Menu.getFoodBySequence(1);

        // then
        assertEquals(9, japaneseFood.size());
        assertTrue(japaneseFood.contains("규동"));
        assertTrue(japaneseFood.contains("스시"));
        assertFalse(japaneseFood.contains("피자"));
    }

    @DisplayName("음식의 이름으로 카테고리의 번호를 가져오는 기능 테스트")
    @Test
    void testGetNameBySequence() {
        // given
        String koreanName = Menu.getNameBySequence(2);
        String asianName = Menu.getNameBySequence(4);

        assertEquals("아시안", asianName);
        assertEquals("한식", koreanName);
    }
}
