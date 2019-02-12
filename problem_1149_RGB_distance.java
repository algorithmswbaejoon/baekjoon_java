import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//1149
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

    private void solve() {
        this.init();
        int number_of_structure = this.nextInt();
        int cost_of_structure[][] = new int[number_of_structure][3];
        int dp[][] = new int[number_of_structure][3];
        for (int index_of_row = 0; index_of_row < number_of_structure; index_of_row++) {
            for (int index_of_col = 0; index_of_col < 3; index_of_col++) {
                cost_of_structure[index_of_row][index_of_col] = this.nextInt();
            }
        }
        for (int index = 0; index < 3; index++) {
            dp[0][index] = cost_of_structure[0][index];
        }
        for (int index_of_row = 1; index_of_row < number_of_structure; index_of_row++) {
            dp[index_of_row][0] = Math.min(dp[index_of_row - 1][1], dp[index_of_row - 1][2]) + cost_of_structure[index_of_row][0];
            dp[index_of_row][1] = Math.min(dp[index_of_row - 1][0], dp[index_of_row - 1][2]) + cost_of_structure[index_of_row][1];
            dp[index_of_row][2] = Math.min(dp[index_of_row - 1][0], dp[index_of_row - 1][1]) + cost_of_structure[index_of_row][2];
        }
        System.out.println(Math.min(dp[number_of_structure - 1][0], Math.min(dp[number_of_structure - 1][1], dp[number_of_structure - 1][2])));
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}