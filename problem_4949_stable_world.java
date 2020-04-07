import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main { // problem 4949 stable world
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

        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String inputLine = this.nextLine();
            if (inputLine.equals(".")) {
                break;
            }
            if (this.isStable(inputLine)) {
                stringBuilder.append("yes");
            } else {
                stringBuilder.append("no");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
        this.end();
    }

    private boolean isStable(String inputLine) {
        Stack<Character> stack = new Stack<>();
        char[] array = inputLine.toCharArray();
        try {
            for (char input : array) {
                if (input == '(' || input == '[') {
                    stack.push(input);
                } else if (input == ')' && stack.pop() != '(') {
                    return false;
                } else if (input == ']' && stack.pop() != '[') {
                    return false;
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
