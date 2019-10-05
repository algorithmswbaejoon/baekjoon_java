import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {//3053 taxi geometry
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer((this.nextLine()));
        }

        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private double nextDouble() {
        return Double.parseDouble(this.next());
    }


    private void init() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solve() {
        this.init();

        int numberRadi = this.nextInt();
        System.out.printf("%.6f\n", numberRadi * numberRadi * Math.PI);
        System.out.printf("%.6f\n", (double) 2 * numberRadi * numberRadi);

        this.end();
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
