import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // problem 10818 the smallest and biggest
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

    private boolean end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void solve() {
        int numberOfData = this.nextInt();
        int max = 0;
        int min = 0;
        if (numberOfData > 0) {
            max = this.nextInt();
            min = max;
        }
        for (int index = 1; index < numberOfData; index++) {
            int inputData = this.nextInt();
            max = Math.max(inputData, max);
            min = Math.min(inputData, min);
        }
        System.out.println(min + " " + max);

        if (!this.end()) {
            System.out.println("program error");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
