package boj_16926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 배열 돌리기 1
    private StringTokenizer _st;
    private BufferedReader _br;

    private static final int[] ROW_ROTATION_DIRECTION = {1, 0, -1, 0};
    private static final int[] COL_ROTATION_DIRECTION = {0, 1, 0, -1};

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    private void solve() {
        this.init();
        int row = nextInt();
        int col = nextInt();

        int numberOfChange = nextInt();

        int[][] arr = new int[row][col];

        for (int indexRow = 0; indexRow < row; indexRow++) {
            for (int indexCol = 0; indexCol < col; indexCol++) {
                arr[indexRow][indexCol] = nextInt();
            }
        }

        int numberOfLayers = Math.min(row, col) / 2;

        for (int indexOfRotationNum = 0; indexOfRotationNum < numberOfChange; indexOfRotationNum++) {
            for (int indexOfLayer = 0; indexOfLayer < numberOfLayers; indexOfLayer++) {
                int edgeCnt = 0;
                int nowRow = indexOfLayer;
                int nowCol = indexOfLayer;
                int tmp = arr[nowRow][nowCol];
                while (edgeCnt < 4) {
                    int nextRow = nowRow + ROW_ROTATION_DIRECTION[edgeCnt];
                    int nextCol = nowCol + COL_ROTATION_DIRECTION[edgeCnt];

                    if (indexOfLayer <= nextRow && nextRow < row - indexOfLayer &&
                            indexOfLayer <= nextCol && nextCol < col - indexOfLayer) {
                        int beforeData = tmp;
                        tmp = arr[nextRow][nextCol];
                        arr[nextRow][nextCol] = beforeData;

                        nowRow = nextRow;
                        nowCol = nextCol;
                    } else {
                        edgeCnt++;
                    }
                }
                arr[indexOfLayer + 1][indexOfLayer] = tmp;
            }
        }

        print(arr);
    }

    private void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] rows : arr) {
            for (int data : rows) {
                sb.append(data).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}