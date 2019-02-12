import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//9465
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
        int how_many_times = this.nextInt();
        StringBuilder sb = new StringBuilder();
        while (how_many_times-- > 0) {
            sb.append(this.solve_do()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private int solve_do() {
        int number_of_stickers = this.nextInt();
        int[][] cost_of_sticker = new int[2][number_of_stickers];
        for (int index_of_row = 0; index_of_row < 2; index_of_row++) {
            for (int index_of_col = 0; index_of_col < number_of_stickers; index_of_col++) {
                cost_of_sticker[index_of_row][index_of_col] = this.nextInt();
            }
        }
        if (number_of_stickers == 1) {
            return Math.max(cost_of_sticker[0][0], cost_of_sticker[1][0]);
        }
        cost_of_sticker[0][1] += cost_of_sticker[1][0];
        cost_of_sticker[1][1] += cost_of_sticker[0][0];

        if (number_of_stickers == 2) {
            return Math.max(cost_of_sticker[0][1], cost_of_sticker[1][1]);
        }

        for (int index = 2; index < number_of_stickers; index++) {
            cost_of_sticker[0][index] += Math.max(cost_of_sticker[1][index - 1], cost_of_sticker[1][index - 2]);
            cost_of_sticker[1][index] += Math.max(cost_of_sticker[0][index - 1], cost_of_sticker[0][index - 2]);
        }
        return Math.max(cost_of_sticker[0][number_of_stickers - 1], cost_of_sticker[1][number_of_stickers - 1]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}