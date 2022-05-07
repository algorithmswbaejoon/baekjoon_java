package boj_2230;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // 수고르기
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(Objects.requireNonNull(this.readLine()));
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    public void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    public void solve() {
        int numberOfNumbers = nextInt();
        int gap = nextInt();
        int[] arr = new int[numberOfNumbers];
        for (int i = 0; i < numberOfNumbers; i++) {
            arr[i] = nextInt();
        }

        Arrays.sort(arr);
        int minGap = Integer.MAX_VALUE;
        int next = 1;
        for (int i = 0; i < numberOfNumbers; i++) {
            while (next < numberOfNumbers && (arr[next] - arr[i] < gap)) {
                next++;
            }
            if (next >= numberOfNumbers) break;

            minGap = Math.min(minGap, arr[next] - arr[i]);
        }

        System.out.println(minGap);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.solve();
    }
}