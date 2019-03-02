import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {//2504
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
        String input_str = this.next();
        if (input_str.length() % 2 == 1) {//무조건 2의 배수의 일이어야함
            System.out.println(0);
            return;
        }

        Stack<Character> stack = new Stack<>();//[,( 을 넣는다
        Stack<Character> stack_for_small = new Stack<>();//( 만 넣음
        Stack<Character> stack_for_large = new Stack<>();//[ 만 넣음
        Stack<Integer> sum_of_all = new Stack<>();//숫자를 입력 (은 -2 [ -3으로 하고 )과 ]이 들어올때 양수로 바뀌고 덧셈 곱으로 바꿔서 다시 push
        char[] char_input_str = input_str.toCharArray();
        for (int index_str = 0; index_str < char_input_str.length; ++index_str) {
            switch (char_input_str[index_str]) {
                case ')':
                    if (stack_for_small.isEmpty()) {
                        System.out.println(0);
                        return;
                    } else {
                        if (stack.peek() == '[') {
                            System.out.println(0);
                            return;
                        } else {
                            if (sum_of_all.peek() > 0) {//곱셈하는 경우
                                int now = sum_of_all.pop() * 2;
                                sum_of_all.pop();
                                if (!sum_of_all.isEmpty() && sum_of_all.peek() > 0) {
                                    now = sum_of_all.pop() + now;
                                }
                                sum_of_all.push(now);
                            } else {//덧셈을 할경우
                                sum_of_all.pop();
                                if (!sum_of_all.isEmpty() && sum_of_all.peek() > 0) {
                                    sum_of_all.push(sum_of_all.pop() + 2);
                                } else {
                                    sum_of_all.push(2);
                                }
                            }
                            stack.pop();
                            stack_for_small.pop();
                        }
                    }
                    break;
                case '(':
                    stack.push('(');
                    stack_for_small.push('(');
                    sum_of_all.push(-2);
                    break;
                case '[':
                    stack.push('[');
                    stack_for_large.push('[');
                    sum_of_all.push(-3);
                    break;
                case ']':
                    if (stack_for_large.isEmpty()) {
                        System.out.println(0);
                        return;
                    } else {
                        if (stack.peek() == '(') {
                            System.out.println(0);
                            return;
                        } else {
                            if (sum_of_all.peek() > 0) {//곱셈일 경우
                                int now = sum_of_all.pop() * 3;
                                sum_of_all.pop();
                                if (!sum_of_all.isEmpty() && sum_of_all.peek() > 0) {//중간에 덧셈이 필요한 경우
                                    now = sum_of_all.pop() + now;
                                }
                                sum_of_all.push(now);
                            } else {//덧셈을 할 경우
                                sum_of_all.pop();
                                if (!sum_of_all.isEmpty() && sum_of_all.peek() > 0) {
                                    sum_of_all.push(sum_of_all.pop() + 3);
                                } else {
                                    sum_of_all.push(3);
                                }
                            }
                            stack.pop();
                            stack_for_large.pop();
                        }

                    }
                    break;
            }
        }
        if (stack.isEmpty()) {
            System.out.println(sum_of_all.pop());
        } else {
            System.out.println(0);
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}

