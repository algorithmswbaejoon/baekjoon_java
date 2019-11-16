import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class DevDay_Problem_2 {
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

        int numberOfPeople = this.nextInt();
        int[] people = new int[numberOfPeople];

        for (int index = 0; index < numberOfPeople; index++) {
            people[index] = this.nextInt();
        }

        int numberTshirt = this.nextInt();
        int[] tshirt = new int[numberTshirt];

        for (int index = 0; index < numberTshirt; index++) {
            tshirt[index] = this.nextInt();
        }

        System.out.println(this.solution(people, tshirt));

        this.end();
    }

    private int solution(int[] people, int[] tshirts) {
        Arrays.sort(people);
        Arrays.sort(tshirts);
        int indexOfTshirt = tshirts.length - 1;
        int indexOfPeople = people.length - 1;
        int count = 0;
        while (indexOfTshirt >= 0 && indexOfPeople >= 0) {
            if (tshirts[indexOfTshirt] >= people[indexOfPeople]) {
                count += 1;
                indexOfPeople -= 1;
                indexOfTshirt -= 1;
            } else {
                indexOfPeople -= 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        new DevDay_Problem_2().solve();
    }
}