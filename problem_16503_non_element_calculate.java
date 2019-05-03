import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//16503_non_element_calculate
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
        int firstNumber = this.nextInt();
        String firstOp = this.next();
        int secondNumber = this.nextInt();
        String secondOp = this.next();
        int thirdNumber = this.nextInt();
        int firstReuslt = this.getOperateTwoNumber(this.getOperateTwoNumber(firstNumber, firstOp, secondNumber), secondOp, thirdNumber);
        int secondReuslt = this.getOperateTwoNumber(firstNumber, firstOp, this.getOperateTwoNumber(secondNumber, secondOp, thirdNumber));
        System.out.println(Math.min(firstReuslt, secondReuslt));
        System.out.println(Math.max(firstReuslt, secondReuslt));
    }

    private int getOperateTwoNumber(int firstNumber, String operation, int secondNumber) {
        if (operation.equals("+")) {
            return firstNumber + secondNumber;
        } else if (operation.equals("-")) {
            return firstNumber - secondNumber;
        } else if (operation.equals("*")) {
            return firstNumber * secondNumber;
        } else {
            return firstNumber / secondNumber;
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}