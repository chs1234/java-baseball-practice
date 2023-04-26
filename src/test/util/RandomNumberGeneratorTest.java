package test.util;

import main.oop.util.RandomNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @Test
    @DisplayName("서로 다른 3자리 수의 랜덤 숫자를 생성한다.")
    void create_number_of_three_different_digits() {
        int[] randomNumbers = randomNumberGenerator.createRandomNumbers();
        int firstPlaceNum = randomNumbers[0];
        int secondPlaceNum = randomNumbers[1];
        int thirdPlaceNum = randomNumbers[2];

        Assertions.assertEquals(3, randomNumbers.length);

        Assertions.assertNotEquals(firstPlaceNum, secondPlaceNum);
        Assertions.assertNotEquals(firstPlaceNum, thirdPlaceNum);
        Assertions.assertNotEquals(secondPlaceNum, thirdPlaceNum);
    }
}
