import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//7569
    private StringTokenizer _st;
    private BufferedReader _br;
    private int count_of_zero;

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

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

    private void solve() {
        this.init();
        int earth = this.nextInt();
        int sun = this.nextInt();
        int mars = this.nextInt();
        int index = 0;
        while (true) {
            if ((index % 15 + 1 == earth) && (index % 28 + 1 == sun) && (index % 19 + 1 == mars)) {
                System.out.println(index + 1);
                break;
            }
            index++;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}


