package programmers_golho;

public class Solution {
    /**
     * <pre>
     *  괄호 만들때 왼쪽에 괄호를 1개 두고 해당 괄호 내부에 여러개 괄호가 들어갈 수 있고 괄호 1개 오른쪽에 괄호를 여러개 둘 수 있다.
     *  내부 괄호 * 외부 괄호 = 결과
     * </pre>
     */
    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int numberOfPair = 2; numberOfPair <= n; numberOfPair++) {
            for (int start = 1; start <= numberOfPair; start++) {
                dp[numberOfPair] += dp[numberOfPair - start] * dp[start - 1];
            }
        }

        return dp[n];
    }
}