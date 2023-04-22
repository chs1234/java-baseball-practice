package oop.domain;

import oop.util.RandomNumberGenerator;

public class Computer {
    private int ballCnt;
    private int strikeCnt;
    private int[] randomNumbers;

    public void clearCnt() {
        ballCnt = 0;
        strikeCnt = 0;
    }

    public void createRandomNumbers() {
        randomNumbers = new RandomNumberGenerator().createRandomNumbers();
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
