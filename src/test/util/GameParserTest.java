package test.util;

import main.oop.util.GameParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameParserTest {

    private static final String NOT_INTEGER = "TEST!@#%";
    private static final String NOT_INCLUDE_NUMBER = "0";
    private static final String DUPLICATE_NUMBER = "112";
    private static final String NOT_RANGE_NUMBER = "1234";

    private static final String NOT_INTEGER_NUMBER_EXCEPTION_MESSAGE = "정수가 아닌 수가 포함되어 있습니다. 게임을 다시 시작하세요.";
    private static final String NOT_INCLUDE_NUMBER_EXCEPTION_MESSAGE = "0은 포함될 수 없습니다.";
    private static final String NOT_RANGE_NUMBER_EXCEPTION_MESSAGE = "세 자리 수가 아니거나 중복된 숫자가 포함되어 있습니다.";

    private final GameParser gameParser = new GameParser();

    @Test
    @DisplayName("3자리 숫자로 구성된 문자열은 3자리 길이의 숫자 배열로 리턴한다.")
    void correct_parse_test() {
        int[] intArray = gameParser.getUserInputValue("123");
        Assertions.assertEquals(3, intArray.length);
    }

    @Test
    @DisplayName("정수가 아닌 문자열은 포함될 수 없다.")
    void not_include_incorrect_string_value() {
        NumberFormatException notIntegerException = Assertions.assertThrows(NumberFormatException.class, ()
                -> gameParser.getUserInputValue(NOT_INTEGER));

        Assertions.assertEquals(NOT_INTEGER_NUMBER_EXCEPTION_MESSAGE, notIntegerException.getMessage());
    }

    @Test
    @DisplayName("입력 받은 숫자들 중 0은 포함될 수 없다.")
    void not_include_zero() {
        NumberFormatException notIncludeNumberException = Assertions.assertThrows(NumberFormatException.class, ()
                -> gameParser.getUserInputValue(NOT_INCLUDE_NUMBER));

        Assertions.assertEquals(NOT_INCLUDE_NUMBER_EXCEPTION_MESSAGE, notIncludeNumberException.getMessage());
    }

    @Test
    @DisplayName("입력 받은 숫자들 중 중복된 숫자가 포함될 수 없다.")
    void not_include_duplicate_number() {
        NumberFormatException duplicateNumberException = Assertions.assertThrows(NumberFormatException.class, () -> {
            gameParser.getUserInputValue(DUPLICATE_NUMBER);
        });

        Assertions.assertEquals(NOT_RANGE_NUMBER_EXCEPTION_MESSAGE, duplicateNumberException.getMessage());
    }
}
