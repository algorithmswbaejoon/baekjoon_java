import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main { // 1662 압춝
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

    private long nextLong() {
        return Long.parseLong(this.next());
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
        String data = this.next();
        Stack<String> stack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();
        for (char eachData : data.toCharArray()) {
            if (eachData == ')') {
                int subStr = this.calEachSubStr(stack, numberStack);
                numberStack.push(subStr);
                stack.push("A");
            } else {
                stack.push(Character.toString(eachData));
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            String eachData = stack.pop();
            if (eachData.equals("A")) {
                result += numberStack.pop();
            } else {
                result += eachData.length();
            }
        }
        System.out.println(result);
    }

    private int calEachSubStr(Stack<String> stack, Stack<Integer> numberStack) {
        int len = 0;
        while (!stack.peek().equals("(")) {
            String data = stack.pop();
            if (data.equals("A")) {
                len += numberStack.pop();
            } else {
                len += data.length();
            }
        }
        stack.pop();
        len *= Integer.parseInt(stack.pop());

        return len;
    }


    public static void main(String[] args) {
        new Main().solve();
    }
}
