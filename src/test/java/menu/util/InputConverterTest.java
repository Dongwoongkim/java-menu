package menu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputConverterTest {

    @DisplayName("입력한 문자열을 ,을 기준으로 분리하여 변환하는 기능 테스트")
    @Test
    void testConvertStringToStringListByDelimiter() {
        // given
        String input = "ab, cd, ef, gh";

        // when
        List<String> result = InputConverter.convertStringToStringListByDelimiter(input);

        // then
        assertEquals(4, result.size());
        assertEquals("ab", result.get(0));
        assertEquals("cd", result.get(1));
        assertEquals("ef", result.get(2));
        assertEquals("gh", result.get(3));
    }

    @DisplayName("이름 사이에 공백이 있어도 ,을 기준으로 분리하여 변환해야 한다.")
    @Test
    void testConvertStringToStringListByDelimiterWithSpaces() {
        // given
        String input = "   ab , cd,  ef ,  gh  ";

        // when
        List<String> result = InputConverter.convertStringToStringListByDelimiter(input);

        // then
        assertEquals(4, result.size());
        assertEquals("ab", result.get(0));
        assertEquals("cd", result.get(1));
        assertEquals("ef", result.get(2));
        assertEquals("gh", result.get(3));
    }

    @DisplayName("빈 값을 입력하는 경우에는 빈 문자열을 리턴한다.")
    @Test
    void testConvertStringToStringListByDelimiterEmptyInput() {
        // given
        String input = "";

        // when
        List<String> result = InputConverter.convertStringToStringListByDelimiter(input);

        // then
        assertTrue(result.get(0).isEmpty());
    }
}
