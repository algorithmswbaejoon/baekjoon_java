import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // problem 2935 noise
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer(Objects.requireNonNull(this.nextLine()));
        }
        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private boolean end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void solve() {
        String firstNumber = this.next();
        int firstNumLength = firstNumber.length();
        String operation = this.next();
        String secondNumber = this.next();
        int secondNumLength = secondNumber.length();
        StringBuilder stringBuilder = new StringBuilder();

        if (operation.equals("*")) { // *
            if (secondNumber.equals("0") || firstNumber.equals("0")) {
                stringBuilder.append("0");
            } else {
                stringBuilder.append(firstNumber);
                for (int index = 0; index < secondNumLength - 1; index++) {
                    stringBuilder.append("0");
                }
            }
        } else { // +
            if (firstNumber.equals("0")) {
                stringBuilder.append(secondNumber);
            } else if (secondNumber.equals("0")) {
                stringBuilder.append(firstNumber);
            } else if (firstNumLength > secondNumLength) {
                stringBuilder.append(firstNumber);
                int gapOfNumbers = firstNumLength - secondNumLength;
                stringBuilder.setCharAt(gapOfNumbers, '1');
            } else if (firstNumLength == secondNumLength) {
                stringBuilder.append(firstNumber);
                stringBuilder.setCharAt(0, '2');
            } else {
                stringBuilder.append(secondNumber);
                int gapOfNumbers = secondNumLength - firstNumLength;
                stringBuilder.setCharAt(gapOfNumbers, '1');
            }
        }
        System.out.println(stringBuilder.toString());

        if (!this.end()) {
            System.out.println("program error");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
