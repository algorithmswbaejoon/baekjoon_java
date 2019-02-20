import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {//1260
    private StringTokenizer _st;
    private BufferedReader _br;

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
        int number_of_vertices = this.nextInt();
        int number_of_edges = this.nextInt();
        int start_vertex = this.nextInt();
        int[][] edges = new int[number_of_vertices + 1][number_of_vertices + 1];
        for (int index_of_input_edges = 0; index_of_input_edges < number_of_edges; index_of_input_edges++) {
            int tail_vertex = this.nextInt();
            int head_vertex = this.nextInt();
            edges[tail_vertex][head_vertex] = 1;
            edges[head_vertex][tail_vertex] = 1;
        }
        System.out.println(this.find_dfs(start_vertex, number_of_vertices, edges));
        System.out.println(this.find_bfs(start_vertex, number_of_vertices, edges));
    }

    private String find_dfs(int start_vertex, int number_of_vertices, int[][] edges) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[number_of_vertices];
        visited[start_vertex - 1] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(start_vertex);
        sb.append(start_vertex).append(" ");
        while (!stack.isEmpty()) {
            int tail_vertex = stack.peek();
            for (int head_vertex = 1; head_vertex <= number_of_vertices; head_vertex++) {
                if (edges[tail_vertex][head_vertex] == 1 && !visited[head_vertex - 1]) {
                    visited[head_vertex - 1] = true;
                    stack.push(head_vertex);
                    sb.append(head_vertex).append(" ");
                    break;
                }
                if (head_vertex == number_of_vertices) {
                    stack.pop();
                }
            }
        }
        return sb.toString();
    }

    private String find_bfs(int start_vertex, int number_of_vertices, int[][] edges) {//bfs알고리즘
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[number_of_vertices];
        visited[start_vertex - 1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start_vertex);//처음 vertex
        sb.append(start_vertex).append(" ");
        while (!queue.isEmpty()) {//너비부터 탐색하기위해 사용
            int tail_vertex = queue.poll();
            for (int head_vertex = 1; head_vertex <= number_of_vertices; head_vertex++) {
                if (edges[tail_vertex][head_vertex] == 1 && !visited[head_vertex - 1]) {
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
