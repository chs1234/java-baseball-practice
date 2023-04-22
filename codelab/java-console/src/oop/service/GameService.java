package oop.service;

import oop.domain.Computer;
import oop.domain.User;
import oop.util.NumberFormatParser;
import oop.util.Validator;
import oop.view.InputView;
import oop.view.ResultView;

import java.util.Scanner;

public class GameService {

    private static final int COMPARE_MAX_COUNT = 3;
    private static final int RETRY = 1;
    private static final int END_GAME = 2;
    private final User user;
    private final Computer computer;
    private final InputView inputView;
    private final ResultView resultView;
    private final Scanner scanner;
    private final Validator validator;
    private final NumberFormatParser numberFormatParser;

    public GameService() {
        scanner = new Scanner(System.in);
        validator = new Validator();
        numberFormatParser = new NumberFormatParser();

        user = new User();
        computer = new Computer();
        inputView = new InputView();
        resultView = new ResultView();
    }

    public void run() {
        startGame();
        endGame();
        askRetry();
    }

    public void startGame() {
        inputView.printInputMessage();
        computer.createRandomNumbers();
        play();
    }

    private void play() {
        int strikeCnt = 0;
        while (strikeCnt != 3) {
            computer.clearCnt();
            user.createNumbers(parsedUserInputValue());

            compareNumbersBetweenUserAndComputer();

            resultView.printCalculateMessage(computer.getBallCnt(), computer.getStrikeCnt());
            inputView.printInputMessage();

            strikeCnt = computer.getStrikeCnt();
        }
    }

    private void compareNumbersBetweenUserAndComputer() {
        int[] userInputNumbers = user.getUserInputNumbers();
        int[] randomNumbers = computer.getRandomNumbers();

        for (int compareIndex = 0; compareIndex < COMPARE_MAX_COUNT; compareIndex++) {
            compare(randomNumbers, userInputNumbers, compareIndex);
        }
    }

    private void compare(int[] randomNumbers, int[] userInputNumbers, int compareIndex) {
        int matchIndex = -1;
        for (int i = 0; i < randomNumbers.length; i++) {
            if (randomNumbers[i] == userInputNumbers[compareIndex]) {
                matchIndex = i;
                break;
            }
        }
        increaseCount(compareIndex, matchIndex);
    }

    private void increaseCount(int compareIndex, int matchIndex) {
        if (compareIndex != matchIndex && matchIndex != -1) {
            computer.increaseBallCnt();
        }
        if (compareIndex == matchIndex) {
            computer.increaseStrikeCnt();
        }
    }

    private int[] parsedUserInputValue() {
        String userInputValue = scanner.nextLine();
        validator.validInputValue(userInputValue);
//        System.out.println("userInputValue = " + userInputValue);

        return numberFormatParser.convertStringToIntArray(userInputValue);
    }

    private void endGame() {
        resultView.printAllMatchMessage();
    }

    private void askRetry() {
        inputView.printRetryMessage();
        if (getRetryUserInputValue() == RETRY)
            run();
    }

    private int getRetryUserInputValue() {
        int retryUserInputValue = Integer.parseInt(scanner.nextLine());
        if (retryUserInputValue != RETRY && retryUserInputValue != END_GAME)
            throw new IllegalArgumentException("0 또는 1을 입력해주세요.");

        return retryUserInputValue;
    }
}
