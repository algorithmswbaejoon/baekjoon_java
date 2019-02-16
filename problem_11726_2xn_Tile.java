import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//11726
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
        int[] array_of_tile = new int[length];
        if (length > 0) {
            array_of_tile[0] = 1;
        }
        if (length > 1) {
            array_of_tile[1] = 2;
        }
        for (int index = 2; index < length; index++) {
            array_of_tile[index] = array_of_tile[index - 1] + array_of_tile[index - 2];
            array_of_tile[index] %= 10007;
        }
        System.out.println(array_of_tile[length - 1]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
