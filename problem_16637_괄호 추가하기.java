import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 16637 괄호 추가하기
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer(Objects.requireNonNull(this.nextLine()));
        }
        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private long nextLong() {
        return Long.parseLong(this.next());
    }

    private void end() {
        try {
            this.bufferedReader.close();
        } catch (IOException ignored) {
        }
    }

    public void solve() {
        int len = this.nextInt();
        String s = this.next();
        char[] strData = s.toCharArray();

        int max = Integer.MIN_VALUE;
        System.out.println(this.dfs(strData, max, strData[0] - '0', 2));

        this.end();
    }


    private int dfs(char[] strData, int max, int headResult, int nowIndex) {
        if (nowIndex >= strData.length) {
            return Math.max(headResult, max);
        }

        char prefixOper = strData[nowIndex - 1];
        int nextMax = max;
        if (nowIndex + 2 < strData.length) {
            int nowPositionResult = this.cal(strData[nowIndex] - '0', strData[nowIndex + 2] - '0', strData[nowIndex + 1]);
            int sumOfHeadAndNow = this.cal(headResult, nowPositionResult, prefixOper);
            nextMax = Math.max(nextMax, this.dfs(strData, max, sumOfHeadAndNow, nowIndex + 4));
        }
        return Math.max(nextMax, this.dfs(strData, nextMax, cal(headResult, strData[nowIndex] - '0', prefixOper), nowIndex + 2));
    }

    private int cal(int fir, int sec, char oper) {
        switch (oper) {
            case '+':
                return fir + sec;
            case '-':
                return fir - sec;
            case '*':
                return fir * sec;
        }

        return fir;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}