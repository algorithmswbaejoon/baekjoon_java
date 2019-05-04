import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {//num1
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
        int numberOfMaxNumber = this.nextInt();
        int dataOfNumber = this.nextInt();//2개
        int[] arrayOfData = new int[dataOfNumber];
        for (int index = 0; index < dataOfNumber; index++) {
            arrayOfData[index] = this.nextInt();
        }

        int[] numbersData = new int[numberOfMaxNumber + 1];
        for (int otherIndex = 0; otherIndex < dataOfNumber; otherIndex++) {
            for (int index = 1; arrayOfData[otherIndex] * index <= numberOfMaxNumber; index++) {
                numbersData[arrayOfData[otherIndex] * index]++;
            }
        }

        int sum = 0;
        for (int index = 0; index < numbersData.length; index++) {
            if (numbersData[index] > 0) {
                sum += index;
            }
        }
        System.out.println(sum);
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}