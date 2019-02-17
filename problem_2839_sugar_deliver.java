import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.StringTokenizer;

public class Main {//2839
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
        int kg_of_suger = this.nextInt();
        int[] case_of_sugar = new int[kg_of_suger + 1];
        case_of_sugar[0] = case_of_sugar[1] = case_of_sugar[2] = -1;
        if (kg_of_suger >= 3) {
            case_of_sugar[3] = 1;
        }
        if (kg_of_suger >= 4) {
            case_of_sugar[4] = -1;
        }
        if (kg_of_suger >= 5) {
            case_of_sugar[5] = 1;
        }
        for (int index = 6; index <= kg_of_suger; index++) {
            int case_1 = -1;
            int case_2 = -1;
            if (case_of_sugar[index - 3] != -1) {
                case_1 = case_of_sugar[index - 3] + 1;
            }
            if (case_of_sugar[index - 5] != -1) {
                case_2 = case_of_sugar[index - 5] + 1;
            }
            if (case_1 >= 0 && case_2 >= 0) {
                case_of_sugar[index] = Math.min(case_1, case_2);
            }
            if (case_1 >= 0 && case_2 < 0) {
                case_of_sugar[index] = case_1;
            }
            if (case_2 >= 0 && case_1 < 0) {
                case_of_sugar[index] = case_2;
            }
            if (case_1 < 0 && case_2 < 0) {
                case_of_sugar[index] = -1;
            }
        }
        System.out.println(case_of_sugar[kg_of_suger]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
