package boj_11003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(Objects.requireNonNull(this.readLine()));
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    public void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    public void solve() {
        int size = nextInt();
        int bound = nextInt();

        Deque<Point> queue = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int num = nextInt();

            while (!queue.isEmpty() && queue.peekLast().nowVal > num) { // 작은 것들 전부 poll
                queue.pollLast();
            } // min val 을 적제하기 위한 방법

            queue.add(new Point(num, i)); // 추가
            int minBoundIndex = i - (bound - 1);
            // front 검색
            if (queue.peek().index < minBoundIndex) {
                queue.poll(); // bound out 인 case 제거
            }
            sb.append(queue.peek().nowVal + " "); // bound 내부에서 min 값 반환
        }

        System.out.println(sb);
    }

    static class Point {
        int nowVal;
        int index;

        public Point(int nowVal, int index) {
            this.nowVal = nowVal;
            this.index = index;
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.solve();
    }
}
