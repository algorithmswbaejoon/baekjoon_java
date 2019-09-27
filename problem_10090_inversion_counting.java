import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {//10090 inversion counting
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

        int numberOfArrayLength = this.nextInt();
        int[] array = new int[numberOfArrayLength];
        for (int index = 0; index < numberOfArrayLength; index++) {
            array[index] = this.nextInt();
        }
        Point result = Main.sortAndCount(array);

        System.out.println(result.count);

        this.end();
    }


    public static final Point sortAndCount(int[] array) {
        if (array.length == 1) {
            return new Point(BigInteger.ZERO, array);
        }
        int[] leftArray = new int[array.length / 2];
        int[] rightArray = new int[array.length - leftArray.length];
        System.arraycopy(array, 0, leftArray, 0, leftArray.length);
        System.arraycopy(array, leftArray.length, rightArray, 0, rightArray.length);
        Point leftResult = sortAndCount(leftArray);
        Point rightResult = sortAndCount(rightArray);
        Point mergedResult = mergeAndCount(leftResult.array, rightResult.array);

        return new Point(leftResult.count.add(rightResult.count).add(mergedResult.count), mergedResult.array);
    }

    private static final Point mergeAndCount(int[] left, int[] right) {
        BigInteger inversionCount = BigInteger.ZERO;
        int indexOfA = 0;
        int indexOfB = 0;
        int[] resultArray = new int[left.length + right.length];
        int indexOfResult = 0;
        int countOfLeft = left.length;
        int countOfRight = right.length;
        while (countOfLeft != 0 && countOfRight != 0) {
            if (left[indexOfA] > right[indexOfB]) {
                inversionCount = inversionCount.add(new BigInteger(String.valueOf(countOfLeft)));
                resultArray[indexOfResult] = right[indexOfB];
                indexOfB += 1;
                countOfRight--;
            } else {
                resultArray[indexOfResult] = left[indexOfA];
                indexOfA += 1;
                countOfLeft--;
            }
            indexOfResult += 1;
        }
        while (indexOfA < left.length) {
            resultArray[indexOfResult] = left[indexOfA];
            indexOfA += 1;
            indexOfResult += 1;
        }
        while (indexOfB < right.length) {
            resultArray[indexOfResult] = right[indexOfB];
            indexOfB += 1;
            indexOfResult += 1;
        }

        return new Point(inversionCount, resultArray);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}


class Point {
    public BigInteger count;
    public int[] array;

    public Point(BigInteger count, int[] array) {
        this.count = count;
        this.array = array;
    }
}