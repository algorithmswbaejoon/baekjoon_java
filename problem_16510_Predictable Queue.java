import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {//16510 Predictable Queue
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void init() {
        this._st = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void solve() {
        this.init();
        int numberOfWork = this.nextInt();
        int numberOfTimes = this.nextInt();
        int[] eachOfWorkTime = new int[numberOfWork];
        for (int index = 0; index < numberOfWork; index++) {
            eachOfWorkTime[index] = this.nextInt();
        }

        int[] canSolveNumber = new int[numberOfTimes];
        Point[] arrayPoint = new Point[numberOfTimes];
        for (int index = 0; index < numberOfTimes; index++) {
            arrayPoint[index] = new Point(index, this.nextInt());
        }

        Arrays.sort(arrayPoint);
        int sumOfTime = 0;
        int indexOfLast = 0;
        for (int index = 0; index <= numberOfWork; index++) {
            if (arrayPoint[0].time < sumOfTime) {
                sumOfTime -= eachOfWorkTime[index - 1];
                indexOfLast = index - 1;
                break;
            }
            if (index == numberOfWork) {
                indexOfLast = index;
                break;
            }
            sumOfTime += eachOfWorkTime[index];
        }
        canSolveNumber[arrayPoint[0].index] = indexOfLast;

        for (int index = 1; index < numberOfTimes; index++) {
            for (int indexOfEachWork = indexOfLast; indexOfEachWork <= numberOfWork; indexOfEachWork++) {
                if (arrayPoint[index].time < sumOfTime) {
                    sumOfTime -= eachOfWorkTime[indexOfEachWork - 1];
                    indexOfLast = indexOfEachWork - 1;
                    break;
                }
                if (indexOfEachWork == numberOfWork) {
                    indexOfLast = indexOfEachWork;
                    break;
                }
                sumOfTime += eachOfWorkTime[indexOfEachWork];
            }
            canSolveNumber[arrayPoint[index].index] = indexOfLast;
        }

        StringBuilder sb = new StringBuilder();
        for (int indexOfArray = 0; indexOfArray < canSolveNumber.length; indexOfArray++) {
            sb.append(canSolveNumber[indexOfArray]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}

class Point implements Comparable<Point> {
    int index;
    int time;

    public Point(int newIndex, int newTime) {
        this.index = newIndex;
        this.time = newTime;
    }

    @Override
    public int compareTo(Point o) {
        if (this.time < o.time) {
            return -1;
        } else if (this.time == o.time) {
            return 0;
        }
        return 1;
    }
}