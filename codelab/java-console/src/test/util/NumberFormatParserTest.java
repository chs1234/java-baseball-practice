package test.util;

import main.oop.util.NumberFormatParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberFormatParserTest {

    private static final String NOT_INTEGER = "TEST!@#%";
    private static final String NOT_INCLUDE_NUMBER = "0";
    private static final String DUPLICATE_NUMBER = "112";
    private static final String NOT_RANGE_NUMBER = "1234";

    private static final String NOT_INTEGER_NUMBER_EXCEPTION_MESSAGE = "정수가 아닌 수가 포함되어 있습니다. 게임을 다시 시작하세요.";
    private static final String NOT_INCLUDE_NUMBER_EXCEPTION_MESSAGE = "0은 포함될 수 없습니다.";
    private static final String NOT_RANGE_NUMBER_EXCEPTION_MESSAGE = "세 자리 수가 아니거나 중복된 숫자가 포함되어 있습니다.";

    private final NumberFormatParser numberFormatParser = new NumberFormatParser();

    @Test
    void lengthTest() {
        int[] intArray = numberFormatParser.convertStringToIntArray("123");
        Assertions.assertEquals(3, intArray.length);
    }

    @Test
    void exceptionTest() {
        NumberFormatException notIntegerException = Assertions.assertThrows(NumberFormatException.class, ()
                -> numberFormatParser.convertStringToIntArray(NOT_INTEGER));
        NumberFormatException notIncludeNumberException = Assertions.assertThrows(NumberFormatException.class, ()
                -> numberFormatParser.convertStringToIntArray(NOT_INCLUDE_NUMBER));
        NumberFormatException duplicateNumberException = Assertions.assertThrows(NumberFormatException.class, () -> {
            numberFormatParser.convertStringToIntArray(DUPLICATE_NUMBER);
            numberFormatParser.convertStringToIntArray(NOT_RANGE_NUMBER);
        });

        Assertions.assertEquals(NOT_INTEGER_NUMBER_EXCEPTION_MESSAGE, notIntegerException.getMessage());
        Assertions.assertEquals(NOT_INCLUDE_NUMBER_EXCEPTION_MESSAGE, notIncludeNumberException.getMessage());
        Assertions.assertEquals(NOT_RANGE_NUMBER_EXCEPTION_MESSAGE, duplicateNumberException.getMessage());
    }
}
