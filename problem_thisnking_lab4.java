import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {//lab4
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
        int numberOfInput = this.nextInt();
        int[] data = new int[53];
        for (int index = 0; index < numberOfInput; index++) {
            data[this.nextInt()]++;
        }
        String strData = this.readLine();
        for (int index = 0; index < numberOfInput; index++) {
            data[this.getIndex(strData.charAt(index))]--;
        }

        for (int index = 0; index < 53; index++) {
            if (data[index] != 0) {
                System.out.println("n");
                return;
            }
        }
        System.out.println("y");
    }

    private int getIndex(char data) {
        if (data == ' ') {
            return 0;
        } else if ('A' <= data && data <= 'Z') {
            return data - 'A' + 1;
        } else if ('a' <= data && data <= 'z') {
            return data - 'a' + 27;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}