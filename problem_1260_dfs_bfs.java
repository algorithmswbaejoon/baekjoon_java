import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//1260
    private StringTokenizer _st;
    private BufferedReader _br;
    private int[][] _edges;
    private int _number_of_vertices;

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

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void solve() {
        this.init();
        this._number_of_vertices = this.nextInt();
        int number_of_edges = this.nextInt();
        int start_vertex = this.nextInt();
        _edges = new int[_number_of_vertices + 1][_number_of_vertices + 1];
        for (int index_of_input_edges = 0; index_of_input_edges < number_of_edges; index_of_input_edges++) {
            int tail_vertex = this.nextInt();
            int head_vertex = this.nextInt();
            _edges[tail_vertex][head_vertex] = 1;
            _edges[head_vertex][tail_vertex] = 1;
        }
        System.out.println(this.find_dfs(start_vertex));
        System.out.println(this.find_bfs(start_vertex));
    }

    private String find_dfs(int start_vertex) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[_number_of_vertices];
        this.dfs(start_vertex, sb, visited);//visited를 따로 전역 변수 처럼 쓰기위해 밖에 선언
        return sb.toString();
    }

    private void dfs(int tail_vertex, StringBuilder sb, boolean[] visited) {//dfs
        visited[tail_vertex - 1] = true;
        sb.append(tail_vertex).append(" ");
        for (int head_vertex = 1; head_vertex <= _number_of_vertices; head_vertex++) {
            if (!visited[head_vertex - 1] && _edges[tail_vertex][head_vertex] == 1) {
                this.dfs(head_vertex, sb, visited);
            }
        }
    }

    private String find_bfs(int start_vertex) {//bfs알고리즘
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[_number_of_vertices];
        visited[start_vertex - 1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start_vertex);//처음 vertex
        sb.append(start_vertex).append(" ");
        while (!queue.isEmpty()) {//너비부터 탐색하기위해 사용
            int tail_vertex = queue.poll();
            for (int head_vertex = 1; head_vertex <= _number_of_vertices; head_vertex++) {
                if (_edges[tail_vertex][head_vertex] == 1 && !visited[head_vertex - 1]) {
                    visited[head_vertex - 1] = true;
                    sb.append(head_vertex).append(" ");
                    queue.add(head_vertex);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
