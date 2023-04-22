package oop.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    public void validInputValue(String userValue) {
        int validNumber;
        try {
            validNumber = Integer.parseInt(userValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("정수가 아닌 수가 포함되어 있습니다. 게임을 다시 시작하세요.");
        }

        if (validNumber == 0)
            throw new NumberFormatException("0은 포함될 수 없습니다.");

        Set<Integer> integers = convertIntegerToSet(validNumber);
        if (integers.size() != 3)
            throw new NumberFormatException("세 자리 수가 아니거나 중복된 숫자가 포함되어 있습니다.");
    }

    public Set<Integer> convertIntegerToSet(int validNumber) {
        return Arrays.stream(String.valueOf(validNumber).split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}
