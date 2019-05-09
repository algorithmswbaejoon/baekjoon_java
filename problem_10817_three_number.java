import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {//10817 three number
    private StringTokenizer _st;
    private BufferedReader _br;

    private String nextLine() {
        try {
            return this._br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.nextLine());
        }
        return this._st.nextToken();
    }

    private long nextLong() {
        return Long.parseLong(this.next());
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
        int[] data = new int[3];
        data[0] = this.nextInt();
        data[1] = this.nextInt();
        data[2] = this.nextInt();
        Arrays.sort(data);
        System.out.println(data[1]);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}