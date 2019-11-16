import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class DevDay_Problem_1 { // numberOfFuniture
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
            this.stringTokenizer = new StringTokenizer(Objects.requireNonNull(this.nextLine()));
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
        int numberOfFuni = this.nextInt();
        int numberOfDate = this.nextInt();
        int beforeFuniNumber;
        int beforeResult;
        int nextResult = 0;
        do {
            beforeFuniNumber = numberOfFuni;
            beforeResult = nextResult;
            nextResult = numberOfFuni / numberOfDate;
            numberOfFuni += (nextResult - beforeResult);
        } while (nextResult != beforeResult);

        System.out.println(beforeFuniNumber);

        this.end();
    }

    public static void main(String[] args) {
        new DevDay_Problem_1().solve();
    }
}