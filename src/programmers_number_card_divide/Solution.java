package programmers_number_card_divide;

import java.util.*;

public class Solution {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int minA = Arrays.stream(arrayA).min().getAsInt();
        int minB = Arrays.stream(arrayB).min().getAsInt();
        Set<Integer> set = new HashSet<>();

        for (int i = 2; i <= minA; i++) {
            if (minA % i == 0) { // 나뉘어 지는 수
                set.add(i);
            }
        }

        for (int i = 2; i <= minB; i++) {
            if (minB % i == 0) { // 나뉘어 지는 수
                set.add(i);
            }
        }

        List<Integer> oneTimeDevideNumbers = new ArrayList<>(set);
        for (Integer oneTimeDevideNumber : oneTimeDevideNumbers) {
            //1번(철수O, 영희X) 체크
            answer = getVaridate(arrayA, arrayB, answer, oneTimeDevideNumber);

            //2번(철수O, 영희X) 체크
            answer = getVaridate(arrayB, arrayA, answer, oneTimeDevideNumber);
        }


        return answer;
    }

    private static int getVaridate(int[] arrayA, int[] arrayB, int nowMaxAnswer, int devideOne) {
        boolean chk = true;
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] % devideOne != 0 || arrayB[i] % devideOne == 0) { // 나뉘어 짐
                chk = false;
                break;
            }
        }
        if (chk) {
            nowMaxAnswer = Math.max(nowMaxAnswer, devideOne);
        }

        return nowMaxAnswer;
    }
}
