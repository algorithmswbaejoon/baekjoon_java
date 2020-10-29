import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // 5557 1학년
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

    private long nextLong() {
        return Long.parseLong(this.next());
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
        int[] arr = new int[numberOfData];
        for (int index = 0; index < arr.length; index += 1) {
            arr[index] = this.nextInt();
        }
        long[] now = new long[21]; // 0 ~ 21
        if (numberOfData > 1) {
            now[arr[0]] = 1;
        }

        for (int index = 1; index < numberOfData - 1; index += 1) { // 마지막 제외
            long[] next = new long[21]; // arr 0 ~ 20
            for (int nowIndex = 0; nowIndex < 21; nowIndex += 1) {
                if (now[nowIndex] > 0) { // 전에 data 유무 검사
                    int plus = nowIndex + arr[index];
                    if (plus <= 20) {
                        next[plus] += now[nowIndex];
                    }

                    int sub = nowIndex - arr[index];
                    if (sub >= 0) {
                        next[sub] += now[nowIndex];
                    }
                }
            }
            now = next;
        }
        System.out.println(now[arr[numberOfData - 1]]);
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}