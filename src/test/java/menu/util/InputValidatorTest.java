package menu.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import menu.exception.EmptyCoachNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class InputValidatorTest {

    @DisplayName("빈 값을 입력하는 경우 예외가 발생해야 한다.")
    @Test
    void testValidateCoachNames() {
        // then
        assertThrows(EmptyCoachNameException.class,
                () -> InputValidator.validateCoachNames(""));
    }
}

