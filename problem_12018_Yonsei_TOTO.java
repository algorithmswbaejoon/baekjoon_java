import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {//12018 Yonsei TOTO
    private StringTokenizer _st;
    private BufferedReader _br;

    private String nextLine() {
        try {
            return this._br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.nextLine());
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
        int numberOfClasses = this.nextInt();
        int myMil = this.nextInt();
        int[] leastMil = new int[numberOfClasses];
        for (int index = 0; index < leastMil.length; index++) {
            int numberOfSupply = this.nextInt();
            int numberOfMax = this.nextInt();
            int[] numberOfInputData = new int[numberOfSupply];
            for (int indexOfSupply = 0; indexOfSupply < numberOfSupply; indexOfSupply++) {
                numberOfInputData[indexOfSupply] = this.nextInt();
            }
            if (numberOfSupply < numberOfMax) {
                leastMil[index] = 1;
            } else {
                Arrays.sort(numberOfInputData);
                leastMil[index] = numberOfInputData[numberOfSupply - numberOfMax];
            }
        }
        Arrays.sort(leastMil);
        int sumOfMil = 0;
        boolean isThat = true;
        for(int index = 0; index < numberOfClasses; index++){
            sumOfMil += leastMil[index];
            if(sumOfMil > myMil){
                System.out.println(index);
                isThat = false;
                break;
            }
        }
        if(isThat){
            System.out.println(numberOfClasses);
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}