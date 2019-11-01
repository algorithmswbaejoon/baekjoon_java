import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {//1992 quard tree
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
            this.stringTokenizer = new StringTokenizer((this.nextLine()));
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

    public void solve() {
        this.init();

        int lengthArray = this.nextInt();
        char[][] map = new char[lengthArray][lengthArray];
        for (int indexOfRow = 0; indexOfRow < lengthArray; ++indexOfRow) {
            String inputData = this.next();
            for (int indexOfCol = 0; indexOfCol < lengthArray; ++indexOfCol) {
                map[indexOfRow][indexOfCol] = inputData.charAt(indexOfCol);
            }
        }

        System.out.println(this.divideAndConquar(map, 0, 0, lengthArray - 1, lengthArray - 1));

        this.end();
    }

    private String divideAndConquar(char[][] array,
                                    int leftRowIndex, int leftColIndex,
                                    int rightRowIndex, int rightColIndex) {
        if (leftColIndex == rightColIndex && leftRowIndex == rightRowIndex) {
            return Character.toString(array[leftRowIndex][rightColIndex]);
        }

        int middleRow = (rightRowIndex + leftRowIndex) / 2;
        int middleCol = (rightColIndex + leftColIndex) / 2;
        String resultOfLeftUpper = this.divideAndConquar(array, leftRowIndex, leftColIndex, middleRow, middleCol);
        String resultOfRightUpper = this.divideAndConquar(array, leftRowIndex, middleCol + 1, middleRow, rightColIndex);
        String resultOfLeftUnder = this.divideAndConquar(array, middleRow + 1, leftColIndex, rightRowIndex, middleCol);
        String resultOfRightUnder = this.divideAndConquar(array, middleRow + 1, middleCol + 1, rightRowIndex, rightColIndex);

        if (resultOfLeftUnder.length() == 1 && resultOfLeftUpper.length() == 1
                && resultOfRightUnder.length() == 1 && resultOfRightUpper.length() == 1) {
            if (resultOfLeftUnder.equals(resultOfLeftUpper) && resultOfLeftUpper.equals(resultOfRightUnder)
                    && resultOfRightUnder.equals(resultOfRightUpper)) {
                return resultOfLeftUnder;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(")
                .append(resultOfLeftUpper).append(resultOfRightUpper).append(resultOfLeftUnder).append(resultOfRightUnder)
                .append(")");

        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
