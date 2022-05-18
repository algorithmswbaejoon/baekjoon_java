package boj_14712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 14712 넴모넴모(Easy)
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

    private static final int[] pointX = {0, -1, -1};
    private static final int[] pointY = {-1, -1, 0};


    int row;
    int col;
    int count = 0;
    boolean[][] isVisited;

    private void solve() {
        row = nextInt();
        col = nextInt();
        count = 0;
        isVisited = new boolean[33][33];

        dfs(1, 1);

        System.out.println(count);
    }

    private void dfs(int nowRow, int nowCol) {
        if (nowRow >= row && nowCol >= col + 1) {
            count++;
            return;
        }

        for (int nextRow = nowRow; nextRow <= row; nextRow++) {
            for (int nextCol = (nextRow == nowRow ? nowCol : 1); nextCol <= col; nextCol++) {
                if (isBoom(isVisited, nextRow, nowCol)) continue;

                isVisited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol + 1);
                isVisited[nextRow][nextCol] = false;
            }
        }
        count++;
    }

    private boolean isBoom(boolean[][] isVisited, int row, int col) {
        int possibleCount = 0;
        for (int i = 0; i < 3; i++) {
            int nextRow = row + pointX[i];
            int nextCol = col + pointY[i];
            if (nextRow >= 0 && nextCol >= 0 && isVisited[nextRow][nextCol]) possibleCount++;
        }

        return possibleCount == 3;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}