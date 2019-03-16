import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//3020_bug
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
        StringBuilder sb = new StringBuilder();
        sb.append(do_times()).append("\n");
        System.out.println(sb.toString());
    }

    private String do_times() {
        int number_of_col = this.nextInt();
        int number_of_height = this.nextInt();
        int half_number_col = number_of_col / 2;
        int[] cost_of_up = new int[number_of_height];
        int[] cost_of_down = new int[number_of_height];
        for (int index_of_input = 0; index_of_input < half_number_col; ++index_of_input) {
            cost_of_down[this.nextInt() - 1] += 1;
            cost_of_up[this.nextInt() - 1] += 1;
        }
        if (half_number_col < number_of_col - half_number_col) {
            cost_of_down[this.nextInt() - 1] += 1;
        }

        for (int index_of_dp = number_of_height - 2; index_of_dp >= 0; --index_of_dp) {
            cost_of_down[index_of_dp] += cost_of_down[index_of_dp + 1];
            cost_of_up[index_of_dp] += cost_of_up[index_of_dp + 1];
        }

        int min_break_obstacle = Integer.MAX_VALUE;
        int number_of_same_space = 1;

        for (int index_of_input_pq = 0; index_of_input_pq < number_of_height; ++index_of_input_pq) {
            int change_number = cost_of_up[number_of_height - index_of_input_pq - 1] + cost_of_down[index_of_input_pq];
            if (change_number < min_break_obstacle) {
                min_break_obstacle = change_number;
                number_of_same_space = 1;
            } else if (change_number == min_break_obstacle) {
                number_of_same_space += 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min_break_obstacle).append(" ").append(number_of_same_space);
        return sb.toString();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}