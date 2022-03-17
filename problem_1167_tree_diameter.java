import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//1167
    private StringTokenizer _st;
    private BufferedReader _br;
    private int max_diameter;
    private int fartest_vertex;

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
        int number_of_vertices = this.nextInt();
        Queue<Pair>[] queue = new Queue[number_of_vertices];
        for (int index_of_input = 0; index_of_input < number_of_vertices; ++index_of_input) {
            int tail_vertex = this.nextInt();
            queue[tail_vertex - 1] = new LinkedList<>();
            while (true) {
                int head_vertex = this.nextInt();
                if (head_vertex == -1) {
                    break;
                }
                int cost = this.nextInt();
                queue[tail_vertex - 1].add(new Pair(head_vertex - 1, cost));
            }
        }

        boolean[] visited = new boolean[number_of_vertices];
        visited[0] = true;
        this.dfs(queue, 0, visited, 0);//지금위치에서 최대 거리의 vertex를 구하기 위함

        visited = new boolean[number_of_vertices];
        max_diameter = 0;

        visited[fartest_vertex] = true;
        this.dfs(queue, fartest_vertex, visited, 0);//1번째에서 가장 멀리있는 vertex에서 다시 제일 먼 거리를 구한다
        System.out.println(max_diameter);//마지막으로 구해진 것을 바탕으로 출력
    }

    private void dfs(Queue<Pair>[] queue, int tail_vertex, boolean[] visited, int now_cost) {//메모리 문제때문에 queue배열로 사용
        Iterator<Pair> iterator = queue[tail_vertex].iterator();
        while (iterator.hasNext()) {
            Pair now_head_vertex = iterator.next();
            if (!visited[now_head_vertex._head_vertex]) {
                visited[now_head_vertex._head_vertex] = true;
                int now_max_cost = now_cost + now_head_vertex._cost;
                if (now_max_cost > max_diameter) {
                    max_diameter = now_max_cost;
                    fartest_vertex = now_head_vertex._head_vertex;
                }
                this.dfs(queue, now_head_vertex._head_vertex, visited, now_max_cost);
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}

class Pair {
    int _head_vertex, _cost;

    public Pair(int head_vertex, int cost) {
        this._head_vertex = head_vertex;
        this._cost = cost;
    }
}
