import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//10844
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
        int number_of_numbers_length = this.nextInt();

        int array[][] = new int[number_of_numbers_length + 1][10];

        for (int index = 1; index < 10; index++) {
            array[1][index] = 1;
        }//0이 되면 안되므로

        for (int index_of_length = 2; index_of_length <= number_of_numbers_length; index_of_length++) {
            for (int index_of_numbers = 0; index_of_numbers < 10; index_of_numbers++) {
                if (index_of_numbers == 0) {
                    array[index_of_length][0] = array[index_of_length - 1][1];
                } else if (index_of_numbers == 9) {
                    array[index_of_length][9] = array[index_of_length - 1][8];
                } else {
                    array[index_of_length][index_of_numbers] = (array[index_of_length - 1][index_of_numbers - 1] + array[index_of_length - 1][index_of_numbers + 1]) % 1000000000;
                }
            }
        }

        long result = 0;
        for (int last_of_index = 0; last_of_index < 10; last_of_index++) {
            result += array[number_of_numbers_length][last_of_index] % 1000000000;
        }
        System.out.println(result % 1000000000);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}