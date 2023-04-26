package main.oop.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberFormatParser {

    private final Validator validator;

    public NumberFormatParser() {
        this.validator = new Validator();
    }

    public int[] convertStringToIntArray(String inputString) {
        int[] intArray = new int[3];

        List<Integer> integerList = convertStringToIntegerList(inputString);
        for (int i = 0; i < integerList.size(); i++) {
            intArray[i] = integerList.get(i);
        }

        return intArray;
    }

    private List<Integer> convertStringToIntegerList(String inputString) {
        validator.validInputValue(inputString);

        return Arrays.stream(inputString.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
