import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//1932
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
        int number_of_height = this.nextInt();
        int[][] cost_of_numbers = new int[number_of_height][number_of_height];
        int max = 0;
        for (int index_of_row = 0; index_of_row < number_of_height; index_of_row++) {
            for (int index_of_col = 0; index_of_col <= index_of_row; index_of_col++) {
                int cost = this.nextInt();
                if (index_of_row > 0) {
                    if (index_of_col == 0) {
                        cost_of_numbers[index_of_row][index_of_col] = cost_of_numbers[index_of_row - 1][index_of_col] + cost;
                    } else if (index_of_col == number_of_height - 1) {
                        cost_of_numbers[index_of_row][index_of_col] = cost_of_numbers[index_of_row - 1][index_of_col - 1] + cost;
                    } else {
                        cost_of_numbers[index_of_row][index_of_col] = Math.max(cost_of_numbers[index_of_row - 1][index_of_col]
                                , cost_of_numbers[index_of_row - 1][index_of_col - 1]) + cost;
                    }
                } else {
                    cost_of_numbers[index_of_row][index_of_col] = cost;
                }
                if (max < cost_of_numbers[index_of_row][index_of_col]) {
                    max = cost_of_numbers[index_of_row][index_of_col];
                }
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
