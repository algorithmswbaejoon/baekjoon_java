import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main { // problem 10773 zero
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
            this.stringTokenizer = new StringTokenizer((Objects.requireNonNull(this.nextLine())));
        }

        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
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

    private void solve() {
        this.init();
        int inpuTimes = this.nextInt();
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < inpuTimes; index++) {
            int inputNumber = this.nextInt();
            if (inputNumber == 0) {
                result -= stack.pop();
            } else {
                stack.push(inputNumber);
                result += inputNumber;
            }
        }
        System.out.println(result);
        this.end();
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
