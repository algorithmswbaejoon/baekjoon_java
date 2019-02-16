import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//2133
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
        int length = this.nextInt();
        if (length % 2 != 0) {
            System.out.println(0);
            return;
        }
        int[] array_of_tile = new int[length + 1];
        if (length >= 0) {
            array_of_tile[0] = 1;
        }
        if (length >= 2) {
            array_of_tile[2] = 3;
        }
        for (int index = 4; index < array_of_tile.length; index += 2) {
            array_of_tile[index] = 3 * array_of_tile[index - 2];
            for (int index_of_otehr = index - 4; index_of_otehr >= 0; index_of_otehr -= 2) {
                array_of_tile[index] += 2 * array_of_tile[index_of_otehr];
            }
        }

        System.out.println(array_of_tile[length]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
