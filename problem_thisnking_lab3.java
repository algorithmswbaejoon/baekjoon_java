import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
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
        int number = this.nextInt() + 1;
        int[] fibo = new int[number];
        if (number < 1) {
            System.out.println(0);
            return;
        } else if (number < 3) {
            System.out.println(1);
            return;
        } else {
            fibo[0] = 1;
            fibo[1] = 1;
        }

        for (int index = 2; index < number; index++) {
            fibo[index] = (fibo[index - 1] + 1 + fibo[index - 2]) % 1000000007;
        }
        System.out.println(fibo[number - 1]);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}