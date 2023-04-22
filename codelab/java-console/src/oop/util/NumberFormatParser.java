package oop.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberFormatParser {

    public int[] convertStringToIntArray(String inputString) {
        int[] intArray = new int[3];

        List<Integer> integerList = convertStringToIntegerList(inputString);
        for (int i = 0; i < integerList.size(); i++) {
            intArray[i] = integerList.get(i);
        }

        return intArray;
    }

    public List<Integer> convertStringToIntegerList(String inputString) {
        return Arrays.stream(inputString.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
