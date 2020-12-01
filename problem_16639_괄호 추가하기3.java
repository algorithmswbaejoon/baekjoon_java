import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 16639 괄호 추가하기 3
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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

    private long nextLong() {
        return Long.parseLong(this.next());
    }

    private boolean end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void solve() { // 모든 곳에 괄호를 쳤다고 가정하고 하기 때문에 완전 탐색으로 간주 됨 -> 모든 경우 다 하기 때문에 캐시 테이블 계속 최신화 해야함
        int len = this.nextInt();
        String formula = this.next();
        char[] formulaArray = formula.toCharArray();

        int[][] maxCache = new int[10][10];
        int[][] minCache = new int[10][10];
        // i~ j 숫자 까지 연산한 결과의 최댓값과 최솟값을 저장하는 캐시가 존재
        // 음 * 음 일 수 있기 때문에 4가지 경우 고려
        /**
         * cal(최댓값, 최댓값)
         * cal(최댓값, 최솟값)
         * cal(최솟값, 최댓값)
         * cal(최솟값, 최솟값)
         * 최종 결과 : maxCache[0][len/2]에 저장되어 있다.
         */
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j += 1) {
                maxCache[i][j] = Integer.MIN_VALUE;
                minCache[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < len / 2 + 1; i++) {
            maxCache[i][i] = formula.charAt(i * 2) - '0';
            minCache[i][i] = formula.charAt(i * 2) - '0';
        } // i,i 에 각 값을 넣어준다.

        for (int numberOfAddedElement = 1; numberOfAddedElement < len / 2 + 1; numberOfAddedElement += 1) { // 한 연산에 포함시키는 최대 개수 -> 먼저 작게 연산 하고 크게 연산해야하기 때문에 이렇게 사용
            for (int startIndex = 0; startIndex < len / 2 + 1 - numberOfAddedElement; startIndex += 1) { // 연산이 시작되는 숫자 base index
                for (int startedAdded = 1, endDeleted = numberOfAddedElement; startedAdded <= numberOfAddedElement; startedAdded++, endDeleted--) {
                    int mid = startIndex + numberOfAddedElement - endDeleted;
                    int end = startIndex + numberOfAddedElement;
                    char operator = formulaArray[2 * mid + 1];
                    int min = this.cal(maxCache[startIndex][mid], maxCache[mid + 1][end], operator); // big oper big
                    int max = min;
                    int tmpResult = this.cal(maxCache[startIndex][mid], minCache[mid + 1][end], operator); // big oper min
                    min = Math.min(min, tmpResult);
                    max = Math.max(max, tmpResult);
                    tmpResult = this.cal(minCache[startIndex][mid], maxCache[mid + 1][end], operator); // min oper big
                    min = Math.min(min, tmpResult);
                    max = Math.max(max, tmpResult);
                    tmpResult = this.cal(minCache[startIndex][mid], minCache[mid + 1][end], operator); // min oper min
                    min = Math.min(min, tmpResult);
                    max = Math.max(max, tmpResult);
                    maxCache[startIndex][end] = Math.max(max, maxCache[startIndex][end]);
                    minCache[startIndex][end] = Math.min(min, minCache[startIndex][end]);
                }
            }
        }
        System.out.println(maxCache[0][len / 2]);
    }

    private int cal(int first, int second, char operation) {
        int result = 0;

        switch (operation) {
            case '+':
                result = first + second;
                break;
            case '-':
                result = first - second;
                break;
            case '*':
                result = first * second;
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
