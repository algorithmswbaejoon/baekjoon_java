import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//15954
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

    private double nextDouble() {
        return Double.parseDouble(this.nextLine());
    }

    private void solve() {
        Queue<Double> q1 = new PriorityQueue<Double>();
        int numberOfProduct = this.nextInt();
        int howManyPro = this.nextInt();
        double[] arrayOfPro = new double[numberOfProduct];
        if (numberOfProduct > 500 || numberOfProduct < 1) {
            return;
        }
        if (howManyPro > numberOfProduct || howManyPro < 1) {
            return;
        }
        for (int index = 0; index < numberOfProduct; index++) {
            arrayOfPro[index] = this.nextDouble();// 전체 수 입력
        }

        for (int removeTheLast = howManyPro - 1; removeTheLast < numberOfProduct; removeTheLast++) {
            for (int indexOfThree = 0; indexOfThree < numberOfProduct - removeTheLast; indexOfThree++) {
                double finalDiv = 0;
                double divid = 0;
                for (int index = indexOfThree; index < indexOfThree + removeTheLast + 1; index++) {
                    divid += arrayOfPro[index];
                }

                divid /= (removeTheLast + 1);

                for (int index = indexOfThree; index < indexOfThree + removeTheLast + 1; index++) {
                    finalDiv += Math.pow(arrayOfPro[index] - divid, 2);
                }
                q1.add(Math.sqrt(finalDiv / (removeTheLast + 1)));
            }
        }
        System.out.println(q1.poll());
    }

    private void run() {
        this.init();
        this.solve();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main main = new Main();
        main.run();
    }
}
