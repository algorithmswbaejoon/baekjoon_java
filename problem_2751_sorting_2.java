import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//2751
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
        int number_of_numbers = this.nextInt();
        int[] stored_numbers_positive = new int[1000000 + 1];
        int[] stored_numbers_non_positive = new int[1000000 + 1];
        for (int index_of_intput = 0; index_of_intput < number_of_numbers; index_of_intput++) {
            int temp = this.nextInt();
            if (temp >= 0) {
                stored_numbers_positive[temp]++;
            } else {
                stored_numbers_non_positive[-temp]++;
            }
        }

        StringBuilder sb_non_positive = new StringBuilder();
        StringBuilder sb_positive = new StringBuilder();

        for (int index = 0; index <= 1000000; index++) {
            int sum_non_positive = stored_numbers_non_positive[1000000 - index];
            int sum_positive = stored_numbers_positive[index];
            if (sum_non_positive > 0) {
                for (int index_of_non_positive = 0; index_of_non_positive < sum_non_positive; index_of_non_positive++) {
                    sb_non_positive.append(-1000000 + index).append("\n");
                }
            }
            if (sum_positive > 0) {
                for (int index_of_sum_postive = 0; index_of_sum_postive < sum_positive; index_of_sum_postive++) {
                    sb_positive.append(index).append("\n");
                }
            }
        }
        System.out.print(sb_non_positive.toString());
        System.out.println(sb_positive.toString());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
