package test.util;

import main.oop.util.RandomNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @Test
    void randomNumbersDuplicateAndLengthTest() {
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
