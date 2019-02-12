import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {//11057
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
        int number_of_increase_number[][] = new int[number_of_structure + 1][10];
        for (int index = 0; index < 10; index++) {
            number_of_increase_number[1][index] = 1;
        }

        for (int index_of_length = 2; index_of_length <= number_of_structure; index_of_length++) {
            for (int index_of_col = 0; index_of_col < 10; index_of_col++) {
                if (index_of_col == 0) {
                    number_of_increase_number[index_of_length][0] = 1;
                } else {
                    number_of_increase_number[index_of_length][index_of_col] = this.sum_of_col(index_of_length, index_of_col, number_of_increase_number);
                }
            }
        }

        long final_number = 0;
        for (int index = 0; index < 10; index++) {
            final_number += number_of_increase_number[number_of_structure][index];
        }
        System.out.println(final_number % 10007);
    }

    private int sum_of_col(int row, int col, int[][] array) {
        long sum = 0;
        for (int index_of_col = 0; index_of_col <= col; index_of_col++) {
            sum += array[row - 1][index_of_col];
        }

        return (int) sum % 10007;
    }

    public static void main(String[] args) {
        test test = new test();
        test.solve();
    }
}
