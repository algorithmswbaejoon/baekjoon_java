import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//2146
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
        int number_of_space = this.nextInt();
        int[][] space = new int[number_of_space][number_of_space];
        for (int index_of_row = 0; index_of_row < number_of_space; index_of_row++) {
            for (int index_of_col = 0; index_of_col < number_of_space; index_of_col++) {
                space[index_of_row][index_of_col] = this.nextInt();
            }
        }

        int[][] divied_space = new int[number_of_space][number_of_space];
        int count = -1;
        Queue<Position> temp_queue = new LinkedList<>();
        for (int index_of_row = 0; index_of_row < number_of_space; index_of_row++) {
            for (int index_of_col = 0; index_of_col < number_of_space; index_of_col++) {
                if (space[index_of_row][index_of_col] == 1) {
                    if (divied_space[index_of_row][index_of_col] == 0) {
                        temp_queue.add(new Position(index_of_row, index_of_col, 0));
                        divied_space[index_of_row][index_of_col] = count--;
                        while (!temp_queue.isEmpty()) {
                            Position now_position = temp_queue.poll();
                            for (int index_of_array = 0; index_of_array < 4; index_of_array++) {
                                int now_row = now_position._row + array_x[index_of_array];
                                int now_col = now_position._col + array_y[index_of_array];
                                if (0 <= now_row && now_row < number_of_space && 0 <= now_col && now_col < number_of_space) {
                                    if (space[now_row][now_col] == 1 && divied_space[now_row][now_col] == 0) {
                                        divied_space[now_row][now_col] = divied_space[now_position._row][now_position._col];
                                        temp_queue.add(new Position(now_row, now_col, 0));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        count = -count;
        Queue<Position>[] queue_space = new Queue[count];
        for (int index_queue = 1; index_queue < count; index_queue++) {
            queue_space[index_queue] = new LinkedList<>();
        }

        for (int index_of_row = 0; index_of_row < number_of_space; index_of_row++) {
            for (int index_of_col = 0; index_of_col < number_of_space; index_of_col++) {
                if (divied_space[index_of_row][index_of_col] != 0) {
                    queue_space[-divied_space[index_of_row][index_of_col]].add(new Position(index_of_row, index_of_col, 0));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int index_of_queue = 1; index_of_queue < count; index_of_queue++) {
            while (!queue_space[index_of_queue].isEmpty()) {
                Position now_position = queue_space[index_of_queue].poll();
                for (int index_of_arrays = 0; index_of_arrays < 4; index_of_arrays++) {
                    int now_row = now_position._row + array_x[index_of_arrays];
                    int now_col = now_position._col + array_y[index_of_arrays];
                    if (0 <= now_row && now_row < number_of_space && 0 <= now_col && now_col < number_of_space) {
                        if (divied_space[now_row][now_col] >= 0 && divied_space[now_row][now_col] != index_of_queue) {
                            divied_space[now_row][now_col] = index_of_queue;
                            queue_space[index_of_queue].add(new Position(now_row, now_col, now_position._count + 1));
                        } else if (divied_space[now_row][now_col] < 0 && divied_space[now_row][now_col] != -index_of_queue && now_position._count > 0) {
                            min = Math.min(min, now_position._count);
                        }
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    class Position {
        int _row, _col, _count;

        public Position(int row, int col, int count) {
            this._row = row;
            this._col = col;
            this._count = count;
        }
    }
}
