import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//2583
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
        int[] array_x = {0, 0, 1, -1};//지금 위치에서 좌우 위아래를 위한 것
        int[] array_y = {1, -1, 0, 0};
        int number_of_row = this.nextInt();
        int number_of_col = this.nextInt();
        int number_of_square = this.nextInt();
        int[][] array = new int[number_of_row][number_of_col];//각각의
        for (int index = 0; index < number_of_square; index++) {
            int first_y = this.nextInt();//첫번째 col
            int first_x = this.nextInt();//첫번째 row
            int second_y = this.nextInt();//두번째 col
            int second_x = this.nextInt();//두번째 row
            for (int index_of_x = first_x; index_of_x < second_x; index_of_x++) {// 위치를 변경 -> 사각형 입력
                for (int index_of_y = first_y; index_of_y < second_y; index_of_y++) {
                    array[index_of_x][index_of_y] = 1;
                }
            }
        }

        Queue<Position> temp_queue = new LinkedList<>();//bfs를 위해서
        int count = -1;//나누기 위해서 count 해준다
        for (int index_of_row = 0; index_of_row < number_of_row; index_of_row++) {
            for (int index_of_col = 0; index_of_col < number_of_col; index_of_col++) {
                if (array[index_of_row][index_of_col] == 0) {
                    array[index_of_row][index_of_col] = count--;
                    temp_queue.add(new Position(index_of_row, index_of_col));
                    while (!temp_queue.isEmpty()) {//이지점부터 bfs를 해준다
                        Position now_position = temp_queue.poll();
                        for (int index_of_array = 0; index_of_array < 4; index_of_array++) {
                            int now_row = now_position._row + array_x[index_of_array];
                            int now_col = now_position._col + array_y[index_of_array];
                            if (0 <= now_row && now_row < number_of_row && 0 <= now_col && now_col < number_of_col) {
                                if (array[now_row][now_col] == 0) {
                                    array[now_row][now_col] = array[now_position._row][now_position._col];
                                    temp_queue.add(new Position(now_row, now_col));
                                }
                            }
                        }
                    }
                }
            }
        }

        int[] count_of_separted = new int[-count - 1];
        for (int index_row = 0; index_row < number_of_row; index_row++) {
            for (int index_col = 0; index_col < number_of_col; index_col++) {
                if (array[index_row][index_col] < 0) {
                    count_of_separted[-array[index_row][index_col] - 1]++;
                }
            }
        }

        Arrays.sort(count_of_separted);

        System.out.println(-count - 1);
        for (int index = 0; index < count_of_separted.length; index++) {
            System.out.print(count_of_separted[index] + " ");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    class Position {
        int _row, _col;

        public Position(int row, int col) {
            this._row = row;
            this._col = col;
        }
    }
}
