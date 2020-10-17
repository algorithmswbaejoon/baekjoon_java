import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1744 tie the numbers
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
        int numbers = this.nextInt();
        int[] array = new int[numbers];
        int[] dp = new int[numbers];

        for (int index = 0; index < numbers; index += 1) {
            array[index] = this.nextInt();
        }
        Arrays.sort(array);
        if (numbers > 0) dp[0] = array[0];
        if (numbers > 1) {
            dp[1] = Math.max(dp[0] + array[1], array[0] * array[1]);
        }

        for (int index = 2; index < numbers; index += 1) {
            dp[index] = Math.max(dp[index - 1] + array[index], dp[index - 2] + array[index - 1] * array[index]);
        }
        System.out.println(dp[numbers - 1]);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
