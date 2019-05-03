import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//16504 fold the paper
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

    private long nextLong() {
        return Long.parseLong(this.next());
    }

    private void init() {
        this._st = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void solve() {
        this.init();
        long numberOfArrayLength = this.nextLong();
        long sum = 0;
        for (long indexOfCol = 0; indexOfCol < numberOfArrayLength; indexOfCol++) {
            for (long indexOfRow = 0; indexOfRow < numberOfArrayLength; indexOfRow++) {
                sum += this.nextInt();
            }
        }
        System.out.println(sum);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}