package menu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import menu.exception.DuplicateCoachNameException;
import menu.exception.InvalidCoachSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoachesTest {

    @DisplayName("팩토리 메소드 생성자 테스트")
    @Test
    void testCreateCoaches() {
        // given
        List<String> coachNames = Arrays.asList("오비", "포비");

        // when
        Coaches coaches = Coaches.createByNames(coachNames);
        List<Coach> coachList = coaches.getCoaches();

        // then
        assertEquals(2, coachList.size());
        assertEquals("오비", coachList.get(0).getName());
        assertEquals("포비", coachList.get(1).getName());
    }

    @DisplayName("유효하지 않은 크기(1)로 코치를 생성 시 예외가 발생한다.")
    @Test
    void testInvalidCoachSize() {
        // when
        List<String> invalidSizeCoachNames = Arrays.asList("John");

        // then
        assertThrows(InvalidCoachSizeException.class, () -> Coaches.createByNames(invalidSizeCoachNames));
    }

    @DisplayName("코치 이름에 중복이 있는 경우 예외가 발생해야 한다.")
    @Test
    void testDuplicateCoachNames() {
        // when
        List<String> duplicateCoachNames = Arrays.asList("John", "John", "Alice");

        // then
        assertThrows(DuplicateCoachNameException.class, () -> Coaches.createByNames(duplicateCoachNames));
    }
}
