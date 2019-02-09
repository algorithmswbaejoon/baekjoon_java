import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {//1463

    StringTokenizer _st;
    BufferedReader _br;

    private void init() {
        this._st = new StringTokenizer("");
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
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    private void sovle() {
        this.init();
        int numberOfElement = this.nextInt();
        int[] array = new int[numberOfElement + 1];
        array[1] = 0;
        for (int index = 2; index <= numberOfElement; index++) {
            array[index] = array[index - 1] + 1;
            if (index % 2 == 0 && index / 2 >= 1) {
                array[index] = Math.min(array[index], array[index / 2] + 1);
            }
            if (index % 3 == 0 && index / 3 >= 1) {
                array[index] = Math.min(array[index], array[index / 3] + 1);
            }
        }
        System.out.println(array[numberOfElement]);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main main = new Main();
        main.sovle();
    }
}
