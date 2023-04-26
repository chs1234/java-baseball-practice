package main.oop.console;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    /**
     * 첨에는 꼴리는 대로 짠다.
     * 1. 랜덤숫자를 만든다.
     * 2. 숫자를 입력받는다. (중복 x, 1 ~ 9 범위의 수, 문자나 제한된 수의 범위를 벗어나면 오류)
     * 3. 랜덤숫자와 입력받은 숫자의 자릿수를 비교하여 볼(포함), 스트라이크(일치) else 낫띵
     * 4. 정답이 맞을 때 까지 반복한다.
     * 5. 정답을 맞추면 다시 시작(1)할건지 종료(2)할 건지 물어본다. (중복 x, 1 ~ 9 범위의 수, 문자나 제한된 수의 범위를 벗어나면 오류)
     * 6. 1번이면 첨부터 시작. 2면 프로그램 종료.
     * // 랜덤 문자 생성
     * // 1 ~ 9 범위의 세 자리 자연수
     * // 각 자리의 수는 중복될 수 없다.
     * // 문자, 허용범위를 넘어서는 수는 익셉션
     */
    public static void main(String[] args) {
        initGame();
    }

    public static void initGame() {
        // 스캐너 객체는 게임이 시작될 때 한번만 만든다.
        Scanner sc = new Scanner(System.in);
        // 볼, 스트라이크 문자열 연결을 위해 StringBuilder 생성 (cf. StringBuffer)
        StringBuilder sb = new StringBuilder();

        // FIXME: 랜덤 문자 생성
        int[] randomNumbers = createRandomNumbers();
        int strikeCnt = 0;
        int ballCnt = 0;

        // while문 탈출조건은 strikeCnt == 3인데, 내부에서 조건이 성립하면 break로 빠져나오게끔 함.
        while (true) {
            // break로 while문을 빠져나오기 전까지는 계속 입력을 받고, 유효성 검사를 계속 실시해야함.
            System.out.print("숫자를 입력해주세요: ");

            String inputValue = sc.nextLine();

            // 유효성 검사 메소드
            isValidNumber(inputValue);

            // List(중복허용o, 인덱스접근 가능, 순서보장) vs Set(중복허용 x, 인덱스 접근불가, 순서 보장 x)
            // java stream api
            List<Integer> inputValueList = Arrays.stream(inputValue.split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int i = 0; i < randomNumbers.length; i++) {
                int finalI = i;

                if (inputValueList.get(i) == randomNumbers[i]) { // 값, 자리 비교
                    strikeCnt++;
                } else if (inputValueList.stream().anyMatch(input -> Objects.equals(input, randomNumbers[finalI]))) { // 포함여부만 체크 anyMatch() 참고
                    ballCnt++;
                }
            }

            // stringbuilder로 문자열 이어붙이기.
            if (ballCnt > 0) {
                sb.append(ballCnt).append("볼").append(" ");
            }
            if (strikeCnt > 0 && strikeCnt != 3) {
                sb.append(strikeCnt).append("스트라이크");
            }

            // 둘다 해당안되면 낫싱
            if (strikeCnt == 0 && ballCnt == 0) {
                System.out.print("낫싱");
            }
            else if (strikeCnt == 3) { // 정답이면 break로 while 빠져나옴.
                System.out.println("3스트라이크");
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
                break;
            }

            // 출력해주고
            System.out.println(sb);

            // while문을 다시 돌기 위해 기존 세팅 클리어
            sb.delete(0, sb.length());
            strikeCnt = 0;
            ballCnt = 0;
        }

        // while문 빠져나왔으니 정답임.
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String s = sc.nextLine(); // 다시 입력받고

        // 조건에 해당하면 다시 실행하든지 종료.
        if (!isLastGame(s))
            initGame();
    }

    public static boolean isLastGame(String lastQuestionNumber) {
        if (lastQuestionNumber.equals("1"))
            return false;
        else if (lastQuestionNumber.equals("2"))
            return true;

        throw new IllegalArgumentException("1 또는 2를 입력해주세요.");
    }

    public static void isValidNumber(String inputValue) {
        if (inputValue.contains("0"))
            throw new IllegalArgumentException("0은 포함될 수 없습니다.");

        try {
            Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }

        if (inputValue.length() != 3)
            throw new IllegalArgumentException("세 자리 정수를 입력해주세요");

        Set<Integer> parsedNumbers = Arrays.stream(inputValue.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        if (parsedNumbers.size() != 3)
            throw new IllegalArgumentException("각 자리의 숫자는 중복될 수 없습니다.");
    }

    public static int[] createRandomNumbers() {
        Random random = new Random();
        int[] numbers = new int[3];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(9) + 1;

            for (int j = 0; j < i; j++) {
                if (numbers[i] == numbers[j]) {
                    i--;
                    break;
                }
            }
        }

        return numbers;
    }
}