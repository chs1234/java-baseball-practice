package main.oop.service;

import main.oop.domain.Computer;
import main.oop.domain.User;
import main.oop.util.NumberFormatParser;
import main.oop.view.InputView;
import main.oop.view.ResultView;

import java.util.Scanner;

public class GameService {

    private static final int COMPARE_MAX_COUNT = 3;
    private static final int RETRY = 1;
    private static final int END_GAME = 2;
    private static final String RETRY_VALID_MESSAGE = "0 또는 1을 입력해주세요.";
     
    private final User user;
    private final Computer computer;
    private final InputView inputView;
    private final ResultView resultView;
    private final Scanner scanner;
    private final NumberFormatParser numberFormatParser;

    public GameService() {
        this.scanner = new Scanner(System.in);
        this.numberFormatParser = new NumberFormatParser();

        this.user = new User();
        this.computer = new Computer();

        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        startGame();
        endGame();
        askRetry();
    }

    private void startGame() {
        clearStrikeAndBallCount();
        createRandomNumbers();
        printInputMessage();
        play();
    }

    private void printInputMessage() {
        inputView.printInputMessage();
    }

    private void createRandomNumbers() {
        computer.createRandomNumbers();
    }

    private void clearStrikeAndBallCount() {
        computer.clearStrikeAndBallCount();
    }

    private void play() {
        while (isNotAllMatch()) {
            clearStrikeAndBallCount();
            createUserNumbersByInputValue();
            compareNumbersBetweenUserAndComputer();
            printCompareResultMessage();
            printInputMessage();
        }
    }

    private boolean isNotAllMatch() {
        return computer.getStrikeCnt() != COMPARE_MAX_COUNT;
    }

    private void createUserNumbersByInputValue() {
        user.createNumbers(getUserInputValue());
    }

    private int[] getUserInputValue() {
        return numberFormatParser.convertStringToIntArray(scanner.nextLine());
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

    private void printCompareResultMessage() {
        resultView.printCompareResultMessage(computer.getBallCnt(), computer.getStrikeCnt());
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
            throw new IllegalArgumentException(RETRY_VALID_MESSAGE);

        return retryUserInputValue;
    }
}
