import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;

public class Main { // leetCode problem 1471 The K Strongest value in an Array
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
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(getStrongest(array, 2)));

        if (!this.end()) {
            System.out.println("program error");
        }
    }

    public int[] getStrongest(int[] arr, int k) {
        int[] cloneArrayData = arr.clone();
        Arrays.sort(cloneArrayData);
        int medIndex = (arr.length - 1) / 2;
        int medData = cloneArrayData[medIndex];
        int[] result = new int[k];
        // Intentional: Reverse order for this demo
        Integer[] array = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(array, (o1, o2) -> {
            int a = o1;
            int b = o2;
            int first = Math.abs(a - medData);
            int second = Math.abs(b - medData);
            if (first > second) {
                return -1;
            } else if (first == second && a > b) {
                return -1;
            }
            return 1;
        });

        for (int index = 0; index < k; index++) {
            result[index] = array[index];
        }
        return result;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
