import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer((this.nextLine()));
        }

        return this.stringTokenizer.nextToken();
    }

    private double nextDouble() {
        return Double.parseDouble(this.next());
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void init() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solve() {
        this.init();
        double haystack = this.nextDouble();
        int n = this.nextInt();
        double result = this.myPow(haystack, n);
        System.out.println(result);

        this.end();
    }

    public double myPow(double x, int n) {
        int change = n;
        if (change < 0) {
            change = -change;
        }

        double result;
        if (change == 1) {
            if (n < 0) {
                return 1 / x;
            }
            return x;
        } else if (change == 0) {
            return 1;
        }
        double before = myPow(x, change / 2);
        if (change % 2 == 0) {
            result = before * before;
        } else {
            result = before * before * x;
        }

        if (result == Double.NEGATIVE_INFINITY || result == Double.POSITIVE_INFINITY) {
            return 0;
        }

        if (n < 0) {
            return 1 / result;
        }

        return result;
    }

    public static void main(String[] args) {
        new Solution().solve();
    }
}