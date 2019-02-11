import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//9095
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
        StringBuilder sb = new StringBuilder();
        int how_many_times = this.nextInt();
        for (int index = 0; index < how_many_times; index++) {
            int number_of_now = this.nextInt();
            int[] array_of_this = new int[number_of_now + 1];
            sb.append(this.now_number_of_all_numbers(array_of_this) + "\n");
        }
        System.out.println(sb.toString());
    }

    private int now_number_of_all_numbers(int[] array) {
        if (array.length < 3) {
            switch (array.length - 1) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
            }
        }
        array[0] = 1;
        array[1] = 1;
        array[2] = 2;
        for (int index = 3; index < array.length; index++) {
            array[index] = array[index - 1] + array[index - 2] + array[index - 3];
        }
        return array[array.length - 1];
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
