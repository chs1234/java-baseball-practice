package oop.domain;

import oop.util.RandomNumberGenerator;

public class Computer {
    private int ballCnt;
    private int strikeCnt;
    private int[] randomNumbers;
    private final RandomNumberGenerator randomNumberGenerator;

    public Computer() {
        this.randomNumberGenerator = new RandomNumberGenerator();
    }

    public void clearStrikeAndBallCount() {
        this.ballCnt = 0;
        this.strikeCnt = 0;
    }

    public void createRandomNumbers() {
        this.randomNumbers = randomNumberGenerator.createRandomNumbers();
    }

    public int getBallCnt() {
        return ballCnt;
    }

    public int getStrikeCnt() {
        return strikeCnt;
    }

    public int[] getRandomNumbers() {
        return randomNumbers;
    }

    public void increaseBallCnt() {
        ballCnt++;
    }

    public void increaseStrikeCnt() {
        strikeCnt++;
    }
}
