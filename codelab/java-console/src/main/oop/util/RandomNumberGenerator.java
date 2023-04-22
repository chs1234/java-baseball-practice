package main.oop.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator {

    public int[] createRandomNumbers() {
        int[] randomNumbers = new int[3];

        List<Integer> integerList = IntStream.range(1, 9).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList);
        randomNumbers[0] = integerList.get(0);
        randomNumbers[1] = integerList.get(1);
        randomNumbers[2] = integerList.get(2);

        return randomNumbers;
    }
}
