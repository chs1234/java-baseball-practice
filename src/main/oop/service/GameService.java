package main.oop.service;

import main.oop.constants.GameRuleConstants;
import main.oop.domain.Computer;
import main.oop.domain.User;
import main.oop.util.GameParser;
import main.oop.view.InputView;
import main.oop.view.ResultView;

import java.util.Scanner;

import static main.oop.constants.GameRuleConstants.COMPARE_MAX_COUNT;
import static main.oop.constants.GameRuleConstants.RETRY;

public class GameService {
     
    private final User user;
    private final Computer computer;
    private final InputView inputView;
    private final ResultView resultView;
    private final Scanner scanner;
    private final GameParser gameParser;

    public GameService() {
        this.scanner = new Scanner(System.in);
        this.gameParser = new GameParser();

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
        return gameParser.getUserInputValue(scanner.nextLine());
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
        return gameParser.getRetryUserInputValue(scanner.nextLine());
    }
}
