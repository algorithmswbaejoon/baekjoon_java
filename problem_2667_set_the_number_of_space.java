import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//2667
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
        int[] array_x = {0, 0, 1, -1};
        int[] array_y = {1, -1, 0, 0};
        int number_of_rad = this.nextInt();
        int[][] space = new int[number_of_rad][number_of_rad];
        for (int index_of_row = 0; index_of_row < number_of_rad; ++index_of_row) {
            String input_line = this.next();
            for (int index_of_col = number_of_rad - 1; index_of_col >= 0; --index_of_col) {
                space[index_of_row][index_of_col] = Integer.parseInt(input_line.substring(index_of_col, index_of_col + 1));
            }
        }

        int count = -1;//각각 지리를 나누기 위해서 사용
        int count_of_space_number = 0;
        Queue<Integer> p_queueu = new PriorityQueue<>();//지역 별로 크기를 저장하기위한 queue 오름차순이기 때문에 pq사용
        Queue<Position> queue = new LinkedList<>();//bfs 사용을 위해서 사용
        for (int index_of_row = 0; index_of_row < number_of_rad; ++index_of_row) {
            for (int index_of_col = 0; index_of_col < number_of_rad; ++index_of_col) {
                if (space[index_of_row][index_of_col] == 1) {
                    queue.add(new Position(index_of_row, index_of_col));
                    space[index_of_row][index_of_col] = count--;//처음으로 1이 나온 지점을 기점으로 사용
                    count_of_space_number = 0;
                    while (!queue.isEmpty()) {//bfs
                        Position now_position = queue.poll();
                        ++count_of_space_number;
                        for (int index_of_array = 0; index_of_array < 4; ++index_of_array) {
                            int now_x = now_position._row + array_x[index_of_array];
                            int now_y = now_position._col + array_y[index_of_array];
                            if (now_x >= 0 && now_x < number_of_rad && now_y >= 0 && now_y < number_of_rad && space[now_x][now_y] == 1) {
                                queue.add(new Position(now_x, now_y));
                                space[now_x][now_y] = space[now_position._row][now_position._col];
                            }
                        }
                    }
                    p_queueu.add(count_of_space_number);
                }
            }
        }

        StringBuilder sb = new StringBuilder();//속도 때문에 builder 사용
        sb.append(-count - 1).append("\n");
        while (!p_queueu.isEmpty()) {
            sb.append(p_queueu.poll()).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}

class Position {
    int _row, _col;

    public Position(int row, int col) {
        this._row = row;
        this._col = col;
    }
}

