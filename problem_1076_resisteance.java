import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//1076
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
        long final_number = 0;
        for (int index = 0; index < 3; ++index) {
            String color = this.next();
            int now = 0;
            if (color.equals("black")) {
                now = 0;
            } else if (color.equals("brown")) {
                now = 1;
            } else if (color.equals("red")) {
                now = 2;
            } else if (color.equals("orange")) {
                now = 3;
            } else if (color.equals("yellow")) {
                now = 4;
            } else if (color.equals("green")) {
                now = 5;
            } else if (color.equals("blue")) {
                now = 6;
            } else if (color.equals("violet")) {
                now = 7;
            } else if (color.equals("grey")) {
                now = 8;
            } else if (color.equals("white")) {
                now = 9;
            }
            if (index == 0) {
                final_number = now;
                final_number *= 10;
            } else if (index == 1) {
                final_number += now;
            } else {
                double ten_mult = Math.pow(10, now);
                final_number *= (int) ten_mult;
            }
        }
        System.out.println(final_number);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
