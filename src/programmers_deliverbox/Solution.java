package programmers_deliverbox;

import java.util.Stack;

public class Solution {
    public int solution(int[] order) {
        Stack<Integer> subCon = new Stack<>();

        int answer = 0;

        int box = 1;
        int orderIndex = 0;
        while (box <= order.length) {
            int nowOrder = order[orderIndex];

            if (subCon.size() > 0 && nowOrder == subCon.peek()) {
                // sub container 조건 충족
                answer++;
                orderIndex++;
                subCon.pop();
            } else if (nowOrder == box) {
                // box 가 order 랑 동일
                answer++;
                orderIndex++;
                box++;
            } else {
                subCon.push(box);
                box++;
            }
        }

        while (orderIndex < order.length && subCon.size() > 0) {
            int nowOrder = order[orderIndex];
            if (nowOrder == subCon.peek()) {
                // sub container 조건 충족
                answer++;
                orderIndex++;
                subCon.pop();
            } else {
                break;
            }
        }

        return answer;
    }
}