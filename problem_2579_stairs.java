import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private StringTokenizer _st;
    private BufferedReader _br;

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
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

    private void solve() {
        this.init();
        int number_of_stairs = this.nextInt();
        int[] cost_of_stairs = new int[number_of_stairs];
        int[] find_dp = new int[number_of_stairs];
        for (int index = 0; index < number_of_stairs; ++index) {
            cost_of_stairs[index] = this.nextInt();
        }
        find_dp[0] = cost_of_stairs[0];
        find_dp[1] = cost_of_stairs[0] + cost_of_stairs[1];
        find_dp[2] = Math.max(cost_of_stairs[0] + cost_of_stairs[2], cost_of_stairs[1] + cost_of_stairs[2]);
        for (int index = 3; index < number_of_stairs; index++) {
            int case_1 = find_dp[index - 2] + cost_of_stairs[index];
            int case_2 = find_dp[index - 3] + cost_of_stairs[index] + cost_of_stairs[index - 1];
            find_dp[index] = Math.max(case_1, case_2);
        }
        System.out.println(find_dp[find_dp.length - 1]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
