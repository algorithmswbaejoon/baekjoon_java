import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//7569

    private StringTokenizer _st;
    private BufferedReader _br;

    private void init() {
        this._st = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
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

    private void solve() {
        this.init();

        int[] array_x = {1, -1, 0, 0, 0, 0};
        int[] array_y = {0, 0, 1, -1, 0, 0};
        int[] array_z = {0, 0, 0, 0, 1, -1};

        int number_of_row = this.nextInt();
        int number_of_col = this.nextInt();
        int number_of_height = this.nextInt();
        int[][][] cost_of_tomatoes = new int[number_of_row][number_of_col][number_of_height];

        int count_of_zero = 0;

        Queue<Position> queue_of_one = new LinkedList<>();

        for (int index_of_height = 0; index_of_height < number_of_height; index_of_height++) {
            for (int index_of_col = 0; index_of_col < number_of_col; index_of_col++) {
                for (int index_of_row = 0; index_of_row < number_of_row; index_of_row++) {
                    cost_of_tomatoes[index_of_row][index_of_col][index_of_height] = this.nextInt();
                    if (cost_of_tomatoes[index_of_row][index_of_col][index_of_height] == 0) {
                        count_of_zero++;//0개수 측정
                    } else if (cost_of_tomatoes[index_of_row][index_of_col][index_of_height] == 1) {
                        queue_of_one.add(new Position(index_of_row, index_of_col, index_of_height, 0));
                    }
                }
            }
        }

        int max_count = 0;

        while (!queue_of_one.isEmpty()) {
            Position now_position = queue_of_one.poll();
            for (int index_of_array = 0; index_of_array < 6; index_of_array++) {
                int other_position_x = now_position.x + array_x[index_of_array];
                int other_position_y = now_position.y + array_y[index_of_array];
                int other_position_z = now_position.z + array_z[index_of_array];
                if ((0 <= other_position_x && other_position_x < number_of_row) && (0 <= other_position_y &&
                        other_position_y < number_of_col) && (0 <= other_position_z && other_position_z < number_of_height)) {
                    if (cost_of_tomatoes[other_position_x][other_position_y][other_position_z] == 0) {
                        cost_of_tomatoes[other_position_x][other_position_y][other_position_z] = 1;
                        queue_of_one.add(new Position(other_position_x, other_position_y, other_position_z, now_position.count + 1));
                        if (max_count < now_position.count + 1) {
                            max_count = now_position.count + 1;
                        }
                        --count_of_zero;
                    }
                }
            }
        }

        if (count_of_zero == 0) {
            System.out.println(max_count);
        } else {
            System.out.println(-1);
        }
    }

    private class Position {
        int x, y, z, count;

        public Position(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}