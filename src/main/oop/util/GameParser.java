package main.oop.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static main.oop.constants.GameRuleConstants.END_GAME;
import static main.oop.constants.GameRuleConstants.RETRY;

public class GameParser {

    public int getRetryUserInputValue(String inputString) {
        validRetryInputValue(inputString);
        return Integer.parseInt(inputString);
    }

    public int[] getUserInputValue(String inputString) {
        int[] intArray = new int[3];

        List<Integer> integerList = convertStringToIntegerList(inputString);
        for (int i = 0; i < integerList.size(); i++) {
            intArray[i] = integerList.get(i);
        }

        return intArray;
    }

    private void validRetryInputValue(String userValue) {
        int validNumber = parseInt(userValue);
        if (validNumber != RETRY && validNumber != END_GAME)
            throw new IllegalArgumentException("1 또는 2를 입력해주세요.");
    }

    private List<Integer> convertStringToIntegerList(String inputString) {
        validInputValue(inputString);

        return Arrays.stream(inputString.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validInputValue(String userValue) {
        int validNumber = parseInt(userValue);
        if (validNumber == 0)
            throw new NumberFormatException("0은 포함될 수 없습니다.");

        Set<Integer> integers = convertIntegerToSet(validNumber);
        if (integers.size() != 3)
            throw new NumberFormatException("세 자리 수가 아니거나 중복된 숫자가 포함되어 있습니다.");
    }

    private Set<Integer> convertIntegerToSet(int validNumber) {
        return Arrays.stream(String.valueOf(validNumber).split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private int parseInt(String userValue) {
        try {
            return Integer.parseInt(userValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("정수가 아닌 수가 포함되어 있습니다. 게임을 다시 시작하세요.");
        }
    }
}
