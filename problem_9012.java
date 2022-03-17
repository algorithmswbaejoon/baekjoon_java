import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//9012

    private BufferedReader _br;
    private StringTokenizer _st;

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
        int number_of_lines = this.nextInt();
        for (int times = 0; times < number_of_lines; times++) {
            if (search_char(this.nextLine())) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }

    private boolean search_char(String input_line) {
        char[] array_of_line = input_line.toCharArray();
        int is_true = 0;
        for (int index = 0; index < array_of_line.length; index++) {
            if (array_of_line[index] == '(') {
                is_true++;
            } else {
                is_true--;
            }
            if (is_true < 0) {
                return false;
            }
        }

        return is_true == 0;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}