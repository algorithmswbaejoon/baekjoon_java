package boj_1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 부분합
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    private void destory() {
        try {
            if (this._br != null) {
                _br.close();
            }
        } catch (Exception ignored) {

        }
    }

    public void run() {
        init();
        solve();
        destory();
    }

    private void solve() {
        int numberOfInput = nextInt();
        int minSum = nextInt();

        int[] arr = new int[numberOfInput];

        for (int i = 0; i < numberOfInput; i++) {
            arr[i] = nextInt();
        }

        int startIndex = 0;
        int endIndex = 0;
        int partSum = 0;
        int minLen = Integer.MAX_VALUE;
        while (startIndex <= endIndex && startIndex < numberOfInput) {
            while (endIndex < numberOfInput && partSum < minSum) {
                partSum += arr[endIndex];
                endIndex++;
            }

            if (partSum >= minSum) {
                minLen = Math.min(minLen, endIndex - startIndex);
            }

            partSum -= arr[startIndex];
            startIndex++;
        }

        if (minLen == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLen);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}