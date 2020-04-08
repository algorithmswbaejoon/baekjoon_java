import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // problem 55 Jump Game
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
            this.stringTokenizer = new StringTokenizer((Objects.requireNonNull(this.nextLine())));
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
        int number = this.nextInt();
        int[] nums = new int[number];
        for (int index = 0; index < number; index++) {
            nums[index] = this.nextInt();
        }
        System.out.println(this.canJump(nums));

        this.end();
    }

    private boolean canJump(int[] nums) {
        int maxIndex = nums.length;
        boolean[] isVisited = new boolean[maxIndex];
        isVisited[0] = true;
        int maxTrueIndex = 0;
        for (int index = 0; index < maxIndex && isVisited[index]; index++) {
            int maxGo = Math.min(index + nums[index], maxIndex - 1);

            for (int indexOfMaxGo = maxTrueIndex + 1; indexOfMaxGo <= maxGo; indexOfMaxGo++) {
                isVisited[indexOfMaxGo] = true;
            }
            maxTrueIndex = maxGo;

            if (maxTrueIndex == maxIndex - 1) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
