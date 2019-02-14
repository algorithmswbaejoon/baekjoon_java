import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//7569
    private StringTokenizer _st;
    private BufferedReader _br;
    private int count_of_zero;

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
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
        int col = this.nextInt();
        int row = this.nextInt();
        int height = this.nextInt();
        int[][][] storage_of_tomato = new int[row][col][height];
        Position max_numbers_position = new Position(row, col, height);
        Queue<Position> oldQ = new LinkedList<>();
        for (int index_of_height = 0; index_of_height < height; ++index_of_height) {
            for (int index_of_row = 0; index_of_row < row; ++index_of_row) {
                for (int index_of_col = 0; index_of_col < col; ++index_of_col) {
                    storage_of_tomato[index_of_row][index_of_col][index_of_height] = this.nextInt();
                    if (storage_of_tomato[index_of_row][index_of_col][index_of_height] == 0) {
                        count_of_zero++;
                    }
                }
            }
        }

        for (int index_of_height = 0; index_of_height < height; ++index_of_height) {
            for (int index_of_col = 0; index_of_col < col; ++index_of_col) {
                for (int index_of_row = 0; index_of_row < row; ++index_of_row) {
                    if (storage_of_tomato[index_of_row][index_of_col][index_of_height] == 1) {
                        Position now_position = new Position(index_of_row, index_of_col, index_of_height);
                        oldQ.add(now_position);
                    }
                }
            }
        }

        int count = 0;
        Queue<Position> newQ = new LinkedList<>();

        while (!oldQ.isEmpty()) {
            this.change_the_zero_to_one(oldQ, newQ, storage_of_tomato, max_numbers_position);
            oldQ = newQ;
            newQ = new LinkedList<>();
            count++;
        }

        if (count_of_zero > 0) {
            System.out.println(-1);
        } else {
            System.out.println(count - 1);
        }
    }

    private void change_the_zero_to_one(Queue<Position> oldQ, Queue<Position> newQ, int[][][] array, Position max_position) {
        while (!oldQ.isEmpty()) {
            Position one_position = oldQ.poll();
            this.insert_all_change_position(one_position, array, newQ, max_position);
        }
    }

    private void insert_all_change_position(Position now_position, int[][][] array, Queue<Position> newQ, Position max_position) {
        if (now_position.row > 0) {
            if (array[now_position.row - 1][now_position.col][now_position.height] == 0) {
                count_of_zero--;
                array[now_position.row - 1][now_position.col][now_position.height] = 1;
                Position temp = new Position(now_position.row - 1, now_position.col, now_position.height);
                newQ.add(temp);
            }
        }
        if (now_position.row < max_position.row - 1) {
            if (array[now_position.row + 1][now_position.col][now_position.height] == 0) {
                count_of_zero--;
                array[now_position.row + 1][now_position.col][now_position.height] = 1;
                Position temp = new Position(now_position.row + 1, now_position.col, now_position.height);
                newQ.add(temp);
            }
        }
        if (now_position.col > 0) {
            if (array[now_position.row][now_position.col - 1][now_position.height] == 0) {
                count_of_zero--;
                array[now_position.row][now_position.col - 1][now_position.height] = 1;
                Position temp = new Position(now_position.row, now_position.col - 1, now_position.height);
                newQ.add(temp);
            }
        }
        if (now_position.col < max_position.col - 1) {
            if (array[now_position.row][now_position.col + 1][now_position.height] == 0) {
                count_of_zero--;
                array[now_position.row][now_position.col + 1][now_position.height] = 1;
                Position temp = new Position(now_position.row, now_position.col + 1, now_position.height);
                newQ.add(temp);
            }
        }
        if (now_position.height > 0) {
            if (array[now_position.row][now_position.col][now_position.height - 1] == 0) {
                count_of_zero--;
                array[now_position.row][now_position.col][now_position.height - 1] = 1;
                Position temp = new Position(now_position.row, now_position.col, now_position.height - 1);
                newQ.add(temp);
            }
        }
        if (now_position.height < max_position.height - 1) {
            if (array[now_position.row][now_position.col][now_position.height + 1] == 0) {
                count_of_zero--;
                array[now_position.row][now_position.col][now_position.height + 1] = 1;
                Position temp = new Position(now_position.row, now_position.col, now_position.height + 1);
                newQ.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    class Position {
        int row, col, height;

        public Position(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}


