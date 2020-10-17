import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // problem 2098 a salesman's cycle
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;
    private final static int INF = 16 * 1_000_000;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    private void end() throws IOException {
        this.bufferedReader.close();
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

    private void run() {
        int numberOfCity = this.nextInt();
        int[][] map = new int[numberOfCity][numberOfCity];
        int[][] dp = new int[numberOfCity][1 << numberOfCity];
        for (int indexOfRow = 0; indexOfRow < numberOfCity; indexOfRow += 1) {
            for (int indexOfCol = 0; indexOfCol < numberOfCity; indexOfCol += 1) {
                map[indexOfRow][indexOfCol] = this.nextInt();
            }
            Arrays.fill(dp[indexOfRow], INF);
        }

        System.out.println(this.tsp(0, 1, numberOfCity, map, dp));
    }

    private int tsp(int nowCity, int visitBit, int numberOfCity, int[][] map, int[][] dp) {
        // 모든 지점을 방문한 경우
        if (visitBit == (1 << numberOfCity) - 1) {
            if (map[nowCity][0] == 0) return INF;
            return map[nowCity][0]; // 모두 둘았는 경우
        }
        // 이미 계산 한 경우
        if (dp[nowCity][visitBit] != INF) return dp[nowCity][visitBit]; // 더이사 갈 곳이 없는 경우

        for (int index = 0; index < numberOfCity; index += 1) {
            int next = visitBit | (1 << index); // 다음 bit 연산 결과
            if ((visitBit & (1 << index)) == 0 && map[nowCity][index] != 0) { // 지나 간적 없고, edge가 연결 되어있는 경우
                dp[nowCity][visitBit] = Math.min(dp[nowCity][visitBit],
                        map[nowCity][index] + tsp(index, next, numberOfCity, map, dp));
            }
        }
        return dp[nowCity][visitBit];
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
