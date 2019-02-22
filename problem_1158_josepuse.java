import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {//1158
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
        int numbers = this.nextInt();
        int how_far = this.nextInt();
        Vector<Integer> vector = new Vector<>();
        for (int index = 1; index <= numbers; index++) {
            vector.add(index);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int now_position = 0;
        while (!vector.isEmpty()) {
            now_position = (now_position + how_far - 1) % vector.size();
            if (vector.size() == 1) {
                sb.append(vector.remove(now_position)).append(">");
            } else {
                sb.append(vector.remove(now_position)).append(", ");
            }
        }
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
