import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {//2231 Decomposition
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

        int number = this.nextInt();
        int data = number;
        int resultData = 0;

        while (data > 0) {
            int result = data;
            int tempData = data;
            while (tempData != 0) {
                result += tempData % 10;
                tempData /= 10;
            }

            if (result == number) {
                resultData = data;
            }
            data--;
        }
        System.out.println(resultData);


        this.end();
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
