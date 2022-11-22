package programmers_four_fundamental_op;

public class Solution {
    public int solution(String[] arr) {
        // size
        int size = arr.length / 2 + 1;
        int opCount = arr.length / 2 + 1;

        // create dp
        int[][] maxDp = new int[size][size];
        int[][] minDp = new int[size][size];

        // set init value
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maxDp[i][j] = Integer.MIN_VALUE;
                minDp[i][j] = Integer.MAX_VALUE;
            }
        }

        // init
        for (int i = 0; i < size; i++) {
            int intVar = Integer.parseInt(arr[i * 2]);
            maxDp[i][i] = intVar;
            minDp[i][i] = intVar;
        }

        for (int middleOpertationCount = 1; middleOpertationCount < opCount; middleOpertationCount++) {
            for (int frontStart = 0; frontStart < opCount - middleOpertationCount; frontStart++) {
                int postEnd = frontStart + middleOpertationCount;
                for (int frontEnd = frontStart; frontEnd < postEnd; frontEnd++) {
                    if (arr[frontEnd * 2 + 1].equals("+")) {
                        maxDp[frontStart][postEnd] = Math.max(maxDp[frontStart][postEnd], maxDp[frontStart][frontEnd] + maxDp[frontEnd + 1][postEnd]);
                        minDp[frontStart][postEnd] = Math.min(minDp[frontStart][postEnd], minDp[frontStart][frontEnd] + minDp[frontEnd + 1][postEnd]);
                    } else {
                        maxDp[frontStart][postEnd] = Math.max(maxDp[frontStart][postEnd], maxDp[frontStart][frontEnd] - minDp[frontEnd + 1][postEnd]);
                        minDp[frontStart][postEnd] = Math.min(minDp[frontStart][postEnd], minDp[frontStart][frontEnd] - maxDp[frontEnd + 1][postEnd]);
                    }
                }
            }
        }

        return maxDp[0][opCount - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"}));
    }
}
