import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class DevDay_Problem_3 {
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

        int numberOfGrade = this.nextInt();
        int[] grade = new int[numberOfGrade];

        for (int index = 0; index < numberOfGrade; index++) {
            grade[index] = this.nextInt();
        }

        int maxDiff = this.nextInt();

        System.out.println(this.solution(grade, maxDiff));

        this.end();
    }

    private int solution(int[] grade, int max_diff) {
        int[] eachGradePerson = new int[10000 + 1];
        for (int gradeData : grade) {
            eachGradePerson[gradeData] += 1;
        }

        int sumForDiffData = 0;
        for (int index = 10000; index >= 10000 - max_diff; index--) {
            sumForDiffData += eachGradePerson[index];
        }

        int beforeMaxIndex = 10000;
        int maxResultPersonNumber = sumForDiffData;

        for (int index = 9999 - max_diff; index > 0; index--) {
            sumForDiffData -= eachGradePerson[beforeMaxIndex];
            sumForDiffData += eachGradePerson[index];
            beforeMaxIndex--;
            maxResultPersonNumber = Math.max(maxResultPersonNumber, sumForDiffData);
        }

        return maxResultPersonNumber;
    }

    public static void main(String[] args) {
        new DevDay_Problem_3().solve();
    }
}