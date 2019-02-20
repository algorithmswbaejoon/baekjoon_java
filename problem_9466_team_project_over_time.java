import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//9466
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
        StringBuilder sb = new StringBuilder();
        int how_many_times = this.nextInt();
        for (int index = 0; index < how_many_times; index++) {
            this.teamp_project(sb);
        }
        System.out.println(sb.toString());
    }

    private void teamp_project(StringBuilder sb) {
        int number_of_students = this.nextInt();
        int[] student_choice = new int[number_of_students + 1];
        int[] union_find = new int[number_of_students + 1];
        boolean[] visited = new boolean[number_of_students];
        Queue<Integer>[] store_parent = new Queue[number_of_students + 1];
        for (int index_row = 1; index_row <= number_of_students; index_row++) {
            student_choice[index_row] = this.nextInt();
            union_find[index_row] = -1;
            store_parent[index_row] = new LinkedList<>();
        }

        for (int index = 1; index <= number_of_students; index++) {
            if (!visited[index - 1]) {
                int tail_vertex = index;
                int head_vertex = student_choice[tail_vertex];
                if (this.parent_of(tail_vertex, union_find) == this.parent_of(head_vertex, union_find)) {
                    int now = head_vertex;
                    while (!visited[now - 1]) {
                        visited[now - 1] = true;
                        now = student_choice[now];
                    }
                } else {
                    this.union(tail_vertex, union_find, student_choice, store_parent);
                }
            }
        }
        int count = 0;
        for (int index = 1; index <= number_of_students; index++) {
            if (!visited[index - 1]) {
                count++;
            }
        }
        sb.append(count).append("\n");
    }

    private int parent_of(int vertex, int[] union_find) {
        int now_vertex = vertex;
        while (union_find[now_vertex] > 0) {
            now_vertex = union_find[now_vertex];
        }
        return now_vertex;
    }

    private void union(int tail_vertex, int[] union_find, int[] student_choice, Queue<Integer>[] queue) {
        union_find[tail_vertex] = student_choice[tail_vertex];
        while (!queue[tail_vertex].isEmpty()) {
            int change_tail_vertex = queue[tail_vertex].poll();
            union_find[change_tail_vertex] = student_choice[tail_vertex];
            queue[student_choice[tail_vertex]].add(change_tail_vertex);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
