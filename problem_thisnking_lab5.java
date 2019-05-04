import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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
        int lineOfCount = this.nextInt();
        List<Integer> arrays = new ArrayList<>();
        for (int i = 0; i < lineOfCount; i++) {
            String inputData = this.readLine();
            String[] inputDataArray = inputData.split(" ");
            for (int index = 0; index < inputDataArray.length; index++) {
                arrays.add(convert(inputDataArray[index]));
            }
        }

        int min;//가장 작은 것
        int minIndex;//인덱스
        Stack<Integer> stack = new Stack();
        int stackMin;
        while (!arrays.isEmpty()) {
            min = Collections.min(arrays);
            while (!stack.isEmpty() && min > stack.peek()) {
                stack.pop();
                continue;
            }
            minIndex = arrays.indexOf(min);
            for (int i = 0; i < minIndex; i++) {
                stack.push(arrays.remove(0));
            }
            if (!stack.isEmpty()) {
                stackMin = Collections.min(stack);
                if (stackMin < arrays.get(0)) {
                    System.out.println("BAD");
                    return;
                }
            }
            arrays.remove(0);
        }

        while (!stack.isEmpty()) {
            int a = stack.pop();
            if (!stack.isEmpty()) {
                int b = stack.pop();
                if (a > b) {
                    System.out.println("BAD");
                    return;
                }
                stack.push(b);
            }
        }
        System.out.println("GOOD");

    }

    public static int convert(String s1) {
        String temp2 = s1.split("-")[1];
        if (s1.split("-")[1].length() == 1) temp2 = "00" + temp2;
        else if (s1.split("-")[1].length() == 2) temp2 = "0" + temp2;
        String temp = (int) (s1.charAt(0)) + temp2;
        return Integer.parseInt(temp);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
