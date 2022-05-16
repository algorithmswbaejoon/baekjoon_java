package boj_11559;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 11559 puyo puyo
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            throw new RuntimeException();
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

    private void destory() {
        try {
            if (this._br != null) {
                _br.close();
            }
        } catch (Exception ignored) {
        }
    }

    public void run() {
        init();
        solve();
        destory();
    }

    private static final int[] pointX = {1, 0, -1, 0};
    private static final int[] pointY = {0, -1, 0, 1};

    private void solve() {
        // 4개 이상 상하좌우 존재
        // 현재 상태에서 몇연쇄 가능?

        int count = 0;

        int row = 12;
        int col = 6;
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            String eachRow = nextLine();
            for (int j = 0; j < col; j++) {
                board[i][j] = eachRow.charAt(j);
            }
        }

        while (true) {
            boolean isFisished = true;
            boolean[][] isVisited = new boolean[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] != '.') {
                        List<Point> connectedPoints = getEliminatePoints(board, isVisited, i, j, row, col);
                        if (connectedPoints.size() >= 4) {
                            isFisished = false;
                            for (Point expiredPoint : connectedPoints) {
                                board[expiredPoint.x][expiredPoint.y] = '.';
                            }
                        }
                    }
                }
            }

            if (isFisished) break;
            fallAfterEliminated(board, row, col);
            count++;

//            printAll(board);
        }

        System.out.println(count);
    }

    private void printAll(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (char[] arr : board) {
            sb.append(arr);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private void fallAfterEliminated(char[][] board, int row, int col) {
        for (int nowCol = 0; nowCol < col; nowCol++) {
            for (int nowRow = row - 1; nowRow > 0; nowRow--) {
                if (board[nowRow][nowCol] == '.') {
                    for (int dist = nowRow - 1; dist >= 0; dist--) {
                        if (board[dist][nowCol] != '.') {
                            board[nowRow][nowCol] = board[dist][nowCol];
                            board[dist][nowCol] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    private List<Point> getEliminatePoints(char[][] board, boolean[][] isVisisted, int nowRow, int nowCol, int row, int col) {

        List<Point> visitedPoints = new LinkedList<>();

        Queue<Point> queue = new LinkedList<>();

        isVisisted[nowRow][nowCol] = true;
        Point firstVisit = new Point(nowRow, nowCol);

        char pointValue = board[nowRow][nowCol];

        queue.add(firstVisit);
        visitedPoints.add(firstVisit);

        while (!queue.isEmpty()) {
            Point nowPoint = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = nowPoint.x + pointX[i];
                int nextCol = nowPoint.y + pointY[i];

                if (0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col && !isVisisted[nextRow][nextCol] && pointValue == board[nextRow][nextCol]) {
                    Point nextPoint = new Point(nextRow, nextCol);
                    queue.add(nextPoint);
                    visitedPoints.add(nextPoint);
                    isVisisted[nextRow][nextCol] = true;
                }
            }
        }

        return visitedPoints;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}