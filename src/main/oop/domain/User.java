package main.oop.domain;

public class User {
    private int[] userInputNumbers;

    public int[] getUserInputNumbers() {
        return userInputNumbers;
    }

    public void createNumbers(int[] userInputNumbers) {
        this.userInputNumbers = userInputNumbers;
    }
}
