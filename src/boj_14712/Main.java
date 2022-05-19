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

    int row;
    int col;
    int count = 0;
    boolean[][] isVisited;

    private void solve() {
        row = nextInt();
        col = nextInt();
        count = 0;
        isVisited = new boolean[row + 1][col + 1];

        dfs(row * col - 1);

        System.out.println(count);
    }

    private void dfs(int num) {
        if (num == -1) {
            count++;
            return;
        }

        int nextRow = num / col;
        int nextCol = num % col;

        if (!(isVisited[nextRow][nextCol + 1] && isVisited[nextRow + 1][nextCol] && isVisited[nextRow + 1][nextCol + 1])) {
            isVisited[nextRow][nextCol] = true;
            dfs(num - 1);
            isVisited[nextRow][nextCol] = false;
        }


        dfs(num - 1);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}