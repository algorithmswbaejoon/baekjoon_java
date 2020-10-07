import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // problem 2263 traveling tree
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
        int row = this.nextInt();
        int col = this.nextInt();
        char[][] map = new char[row][col];
        for (int index = 0; index < row; index += 1) {
            String input = this.next();
            for (int indexOfCol = 0; indexOfCol < col; indexOfCol += 1) {
                map[index][indexOfCol] = input.charAt(indexOfCol);
            }
        }

        int result = 0;
        for (int indexOfRow = 0; indexOfRow < row; indexOfRow += 1) {
            for (int indexOfCol = 0; indexOfCol < col; indexOfCol += 1) {
                Queue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(point -> point.dept));
                boolean[][] isVisited = new boolean[row][col];
                if (!isVisited[indexOfRow][indexOfCol] && map[indexOfRow][indexOfCol] == 'L') {
                    isVisited[indexOfRow][indexOfCol] = true;
                    queue.add(new Point(indexOfRow, indexOfCol, 0));
                    result = Math.max(result, this.bfsOfMap(queue, map, isVisited, row, col));
                }
            }
        }

        System.out.println(result);
    }

    private int bfsOfMap(Queue<Point> queue, char[][] map, boolean[][] isVisited, int row, int col) {
        int[] changeRow = new int[]{1, 0, -1, 0};
        int[] changeCol = new int[]{0, -1, 0, 1};
        int maxDept = 0;
        while (!queue.isEmpty()) {
            Point nowPoint = queue.poll();
            maxDept = Math.max(maxDept, nowPoint.dept);
            for (int index = 0; index < 4; index += 1) {
                int changedRowPoint = nowPoint.x + changeRow[index];
                int changedColPoint = nowPoint.y + changeCol[index];
                if (0 <= changedColPoint && changedColPoint < col && 0 <= changedRowPoint && changedRowPoint < row &&
                        !isVisited[changedRowPoint][changedColPoint] && map[changedRowPoint][changedColPoint] == 'L') {
                    isVisited[changedRowPoint][changedColPoint] = true;
                    queue.add(new Point(changedRowPoint, changedColPoint, nowPoint.dept + 1));
                }
            }
        }

        return maxDept;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

class Point {
    int x;
    int y;
    int dept;

    public Point(int x, int y, int dept) {
        this.x = x;
        this.y = y;
        this.dept = dept;
    }
}