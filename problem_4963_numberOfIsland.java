import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//4963_number of island
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
        StringBuilder sb = new StringBuilder();
        int col = this.nextInt();
        int row = this.nextInt();
        while (!(row == 0 && col == 0)) {
            sb.append(doTimes(row, col)).append("\n");
            col = this.nextInt();
            row = this.nextInt();
        }
        System.out.println(sb.toString());
    }

    private String doTimes(int row, int col) {
        int[] arrayOfRow = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] arrayOfCol = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[][] space = new int[row][col];

        for (int indexOfRow = 0; indexOfRow < row; ++indexOfRow) {
            for (int indexOfCol = 0; indexOfCol < col; ++indexOfCol) {
                space[indexOfRow][indexOfCol] = this.nextInt();
            }
        }

        Queue<Point> queue = new LinkedList<>();
        int spaceData = -1;
        int[][] spaceOfDivide = new int[row][col];
        for (int indexOfRow = 0; indexOfRow < row; ++indexOfRow) {
            for (int indexOfCol = 0; indexOfCol < col; ++indexOfCol) {
                if (space[indexOfRow][indexOfCol] == 1 && spaceOfDivide[indexOfRow][indexOfCol] == 0) {
                    spaceOfDivide[indexOfRow][indexOfCol] = spaceData;
                    queue.add(new Point(indexOfRow, indexOfCol));
                    while (!queue.isEmpty()) {
                        Point nowPoint = queue.poll();
                        for (int indexOfArray = 0; indexOfArray < 8; ++indexOfArray) {
                            int changeOfX = nowPoint.x + arrayOfRow[indexOfArray];
                            int changeOfY = nowPoint.y + arrayOfCol[indexOfArray];
                            if (0 <= changeOfX && changeOfX < row && 0 <= changeOfY && changeOfY < col &&
                                    space[changeOfX][changeOfY] == 1 && spaceOfDivide[changeOfX][changeOfY] == 0) {
                                spaceOfDivide[changeOfX][changeOfY] = spaceData;
                                queue.add(new Point(changeOfX, changeOfY));
                            }
                        }
                    }
                    spaceData -= 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(-spaceData - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}