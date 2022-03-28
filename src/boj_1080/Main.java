package boj_1080;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 행렬
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

    private void solve() {
        int row = nextInt();
        int col = nextInt();

        int[][] firstMatrix = new int[row][col];
        int[][] nextMatrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            String line = nextLine();
            for (int j = 0; j < col; j++) {
                firstMatrix[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < row; i++) {
            String line = nextLine();
            for (int j = 0; j < col; j++) {
                nextMatrix[i][j] = line.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int i = 0; i < row - 2; i++) {
            for (int j = 0; j < col - 2; j++) {
                if (firstMatrix[i][j] != nextMatrix[i][j]) {
                    change(firstMatrix, i, j);
                    count++;
                }
            }
        }

        if (isSameMatrix(firstMatrix, nextMatrix)) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }

    private boolean isSameMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            return false;
        }
        if (firstMatrix.length > 0 && firstMatrix[0].length != secondMatrix[0].length) {
            return false;
        }

        int row = firstMatrix.length;
        int col = firstMatrix[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (firstMatrix[i][j] != secondMatrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void change(int[][] arr, int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                arr[i][j] ^= 1;
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}