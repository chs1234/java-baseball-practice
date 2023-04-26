package main.oop.view;

public class ResultView {

    public void printCompareResultMessage(int ballCnt, int strikeCnt) {
        if (ballCnt == 0 && strikeCnt == 0) {
            System.out.println("낫싱");
        }
        if (ballCnt > 0 && strikeCnt == 0) {
            System.out.println(ballCnt + "볼");
        }
        if (ballCnt == 0 && strikeCnt > 0) {
            System.out.println(strikeCnt + "스트라이크");
        }
        if (ballCnt > 0 && strikeCnt > 0) {
            System.out.println(ballCnt + "볼 " + strikeCnt + "스트라이크");
        }
    }

    public void printAllMatchMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
