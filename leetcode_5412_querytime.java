import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // leetcode querytime
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

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
        System.out.println(this.busyStudent(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10}, 5));

        try {
            this.end();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int numberOfStudyingStudent = 0;

        for (int index = 0; index < startTime.length; index++) {
            if (startTime[index] <= queryTime && queryTime <= endTime[index]) {
                numberOfStudyingStudent++;
            }
        }

        return numberOfStudyingStudent;
    }


    public static void main(String[] args) {
        new Main().run();
    }
}
