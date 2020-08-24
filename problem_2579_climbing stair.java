import java.util.*;

class Main { // 2579 계단 오르기
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfStair = input.nextInt();
        int[] stair = new int[numberOfStair];
        for (int index = 0; index < numberOfStair; index++) {
            stair[index] = input.nextInt();
        }
        int[] dp = new int[numberOfStair];
        if (numberOfStair > 0)
            dp[0] = stair[0];
        if (numberOfStair > 1)
            dp[1] = stair[0] + stair[1];
        if (numberOfStair > 2) {
            dp[2] = Math.max(stair[0] + stair[2], stair[1] + stair[2]);
        }
        for (int index = 3; index < numberOfStair; index++) {
            dp[index] = Math.max(dp[index - 3] + stair[index] + stair[index - 1],
                    dp[index - 2] + stair[index]);
        }
        System.out.println(dp[numberOfStair - 1]);
    }
}