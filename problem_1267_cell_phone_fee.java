import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//1267
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
        int how_many = this.nextInt();
        int sum_of_price_for_y = 0;
        int sum_of_price_for_m = 0;
        for (int index_of_times = 0; index_of_times < how_many; ++index_of_times) {
            int now_price = this.nextInt();
            sum_of_price_for_y += now_price / 30 * 10;
            sum_of_price_for_m += now_price / 60 * 15;
            if (now_price % 30 >= 0) {
                sum_of_price_for_y += 10;
            }
            if (now_price % 60 >= 0) {
                sum_of_price_for_m += 15;
            }
        }
        if (sum_of_price_for_m > sum_of_price_for_y) {
            System.out.println("Y " + sum_of_price_for_y);
        } else if (sum_of_price_for_m == sum_of_price_for_y) {
            System.out.println("Y M " + sum_of_price_for_m);
        } else {
            System.out.println("M " + sum_of_price_for_m);
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
