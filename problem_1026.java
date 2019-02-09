import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {//1026
    private BufferedReader _br;
    private StringTokenizer _token;

    private void init() {
        this._token = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    private String nextLine() {
        while (!this._token.hasMoreTokens()) {
            this._token = new StringTokenizer(this.readLine());
        }
        return this._token.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }// 인트형으로 하기

    private long solve() {
        int selectNum = this.nextInt();
        int[] firstArray = new int[selectNum];
        int[] secondArray = new int[selectNum];
        for (int index = 0; index < selectNum; index++) {
            firstArray[index] = this.nextInt();
            if (firstArray[index] > 100 || firstArray[index] < 0) {
                return -1;
            }
        }
        for (int index = 0; index < selectNum; index++) {
            secondArray[index] = this.nextInt();
            if (secondArray[index] > 100 || secondArray[index] < 0) {
                return -1;
            }
        }
        long finalData = 0;
        Arrays.sort(firstArray);
        Arrays.sort(secondArray);
        for (int index = 0; index < selectNum; index++) {
            finalData += firstArray[index] * secondArray[selectNum - index - 1];
        }
        return finalData;
    }

    private void run() {
        this.init();
        long solved = this.solve();
        if (solved == -1) {

        } else {
            System.out.println(solved);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main main = new Main();
        main.run();
    }
}
