import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//2741 N찍기
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
        long number = this.nextLong();
        StringBuilder sb = new StringBuilder();
        for (long index = 1; index <= number; index += 1) {
            sb.append(index).append("\n");
        }
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}