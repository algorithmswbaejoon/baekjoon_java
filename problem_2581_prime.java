import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        int little_number = this.nextInt();
        int max_number = this.nextInt();
        int min_prim = 0;
        int sum_middle = 0;

        for (int index = little_number; index <= max_number; index++) {
            if (this.is_prime(index)) {
                sum_middle += index;
                if (min_prim == 0) {
                    min_prim = index;
                }
            }
        }


        if (sum_middle != 0) {
            System.out.println(sum_middle);
            System.out.println(min_prim);
        } else {
            System.out.println(-1);
        }
    }

    private boolean is_prime(int number) {
        if (number == 1) {
            return false;
        }

        for (int index = 2; index < number; index++) {
            if (number % index == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
