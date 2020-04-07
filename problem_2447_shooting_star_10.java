import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // problem 2447 shooting star 10
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
        char[][] arrayOfStars = new char[number][number];
        StringBuilder stringBuilder = new StringBuilder();

        for (int index = 0; index < number; index++) {
            Arrays.fill(arrayOfStars[index], ' ');
        }

        this.insertStarChar(number, 0, 0, arrayOfStars);
        for (char[] rowArray : arrayOfStars) {
            for (char eachStar : rowArray) {
                stringBuilder.append(eachStar);
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());

        this.end();
    }

    private void insertStarChar(int number, int minRowNumber, int minColNumber, char[][] arrayOfStars) {
        if (number == 1) {
            arrayOfStars[minRowNumber][minColNumber] = '*';
        } else {
            int newNumber = number / 3;
            int lastNewNumber = newNumber * 2;

            for (int indexOfRow = minRowNumber; indexOfRow < minRowNumber + number; indexOfRow += newNumber) {
                for (int indexOfCol = minColNumber; indexOfCol < minColNumber + number; indexOfCol += newNumber) {
                    if (indexOfRow >= minRowNumber + newNumber && indexOfRow < minRowNumber + lastNewNumber
                            && minColNumber + newNumber <= indexOfCol && indexOfCol < minColNumber + lastNewNumber) {
                        continue;
                    }
                    this.insertStarChar(newNumber, indexOfRow, indexOfCol, arrayOfStars);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
