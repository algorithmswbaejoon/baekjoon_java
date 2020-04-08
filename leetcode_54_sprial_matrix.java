import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // problem 54 leetcode spiral-matrix
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
            this.stringTokenizer = new StringTokenizer((Objects.requireNonNull(this.nextLine())));
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

    private void solve() {
        this.init();
        int m = this.nextInt();
        int n = this.nextInt();
        int[][] matrix = new int[m][n];
        for (int[] innerMatrix : matrix) {
            for (int index = 0; index < n; index++) {
                innerMatrix[index] = this.nextInt();
            }
        }
        System.out.println(this.sprialOrder(matrix));

        this.end();
    }

    private List<Integer> sprialOrder(int[][] matrix) {
        List<Integer> listOfSprialOrder = new ArrayList<>();
        int row = matrix.length;
        if (row == 0 || matrix[0].length == 0) {
            return listOfSprialOrder;
        }

        int col = matrix[0].length;
        boolean[][] isVisited = new boolean[row][col];
        int direction = 0; // 0 : right, 1 : under, 2 : left, 3 : upper
        int nowCol = 0;
        int nowRow = 0;
        while (!isVisited[nowRow][nowCol]) {
            listOfSprialOrder.add(matrix[nowRow][nowCol]);
            isVisited[nowRow][nowCol] = true;
            switch (direction) {
                case 0:
                    if (nowCol == col - 1 || isVisited[nowRow][nowCol + 1]) {
                        direction = 1;
                        if (nowRow < row - 1) {
                            ++nowRow;
                        }
                    } else {
                        ++nowCol;
                    }
                    break;
                case 1:
                    if (nowRow == row - 1 || isVisited[nowRow + 1][nowCol]) {
                        direction = 2;
                        if (nowCol > 0) {
                            --nowCol;
                        }
                    } else {
                        ++nowRow;
                    }
                    break;
                case 2:
                    if (nowCol == 0 || isVisited[nowRow][nowCol - 1]) {
                        direction = 3;
                        if (nowRow > 0) {
                            --nowRow;
                        }
                    } else {
                        --nowCol;
                    }
                    break;
                default:
                    if (nowRow == 0 || isVisited[nowRow - 1][nowCol]) {
                        direction = 0;
                        if (nowCol < col - 1) {
                            ++nowCol;
                        }
                    } else {
                        --nowRow;
                    }
                    break;
            }
        }

        return listOfSprialOrder;
    }


    public static void main(String[] args) {
        new Main().solve();
    }
}
