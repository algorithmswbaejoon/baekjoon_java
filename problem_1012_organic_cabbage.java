import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // problem 1012 organic cabbage
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
        int numberOfTimes = this.nextInt();
        StringBuilder stringBuilder = new StringBuilder();
        int[] xForChanging = {1, 0, 0, -1};
        int[] yForChanging = {0, 1, -1, 0};

        for (int index = 0; index < numberOfTimes; index++) {
            stringBuilder.append(this.searchNumberOfWarm(xForChanging, yForChanging)).append("\n");
        }

        System.out.println(stringBuilder.toString());

        try {
            this.end();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    private int searchNumberOfWarm(int[] xForChanging, int[] yForChanging) {
        int numberOfRealWarm = 0;
        int numberOfRow = this.nextInt();
        int numberOfCol = this.nextInt();
        int numberOfWarms = this.nextInt();
        int[][] farmSpace = new int[numberOfRow][numberOfCol];
        List<Point> listOfVegetablePlace = new LinkedList<>();
        for (int indexOfInputWarms = 0; indexOfInputWarms < numberOfWarms; indexOfInputWarms++) {
            Point newPoint = new Point(this.nextInt(), this.nextInt());
            farmSpace[newPoint.x][newPoint.y] = 1;
            listOfVegetablePlace.add(newPoint);
        }
        Queue<Point> warmDetectedPlace = new LinkedList<>();

        while (!listOfVegetablePlace.isEmpty()) {
            Point pointOfFarm = listOfVegetablePlace.remove(0);
            if (farmSpace[pointOfFarm.x][pointOfFarm.y] != 1) {
                continue;
            }
            warmDetectedPlace.add(pointOfFarm);
            farmSpace[pointOfFarm.x][pointOfFarm.y] = 2;
            numberOfRealWarm += 1;

            while (!warmDetectedPlace.isEmpty()) {
                Point nowPoint = warmDetectedPlace.poll();
                for (int index = 0; index < 4; index++) {
                    int nextX = nowPoint.x + xForChanging[index];
                    int nextY = nowPoint.y + yForChanging[index];
                    if (0 <= nextX && nextX < numberOfRow && 0 <= nextY && nextY < numberOfCol && farmSpace[nextX][nextY] == 1) {
                        farmSpace[nextX][nextY] = 2;
                        warmDetectedPlace.add(new Point(nextX, nextY));
                    }
                }
            }
        }

        return numberOfRealWarm;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
