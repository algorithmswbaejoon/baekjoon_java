import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {//lab2
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
        int money = this.nextInt();
        int divide = this.nextInt();
        int sumDivide = 0;
        int temp = money;
        while (temp >= divide) {
            temp /= divide;
            sumDivide += temp;

        }
        System.out.println(money + sumDivide);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}