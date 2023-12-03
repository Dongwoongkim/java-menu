package menu.model.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import menu.exception.InvalidCoachNameSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("코치 이름의 길이가 2~4사이의 이름이 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "abcde"})
    void validateLengthFailTest(String name) {
        // then
        assertThrows(InvalidCoachNameSizeException.class, () -> new Name(name));
    }

    @DisplayName("코치 이름의 길이가 2~4사이의 이름인 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"ab", "abc", "abcd"})
    void validateLengthSuccessTest(String name) {
        // then
        assertDoesNotThrow(() -> new Name(name));
    }
}
