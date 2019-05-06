import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//1912_연속합
    private StringTokenizer _st;
    private BufferedReader _br;

    private String nextLine() {
        try {
            return this._br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.nextLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void init() {
        this._st = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void solve() {
        this.init();
        int numberOfData = this.nextInt();
        int[] data = new int[numberOfData];
        int[] dp = new int[numberOfData];
        int max = Integer.MIN_VALUE;
        for (int index = 0; index < numberOfData; index++) {
            data[index] = this.nextInt();
        }

        if (numberOfData > 0) {
            dp[0] = data[0];
            max = Math.max(max, dp[0]);
        }

        for (int index = 1; index < numberOfData; index++) {
            dp[index] = Math.max(dp[index - 1] + data[index], data[index]);
            max = Math.max(dp[index], max);
        }
        System.out.println(max);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}