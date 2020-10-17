import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1865
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer(Objects.requireNonNull(this.nextLine()));
        }
        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private boolean end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void solve() {
        int numberOfTest = this.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < numberOfTest; index++) {
            if (this.todo()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb.toString());
    }

    private boolean todo() {
        int numberOfState = this.nextInt();
        int numberOfRoad = this.nextInt();
        int numberOfWarmwhole = this.nextInt();

        int[] weight = new int[numberOfState + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);

        List<Edge> edgesList = new ArrayList<>();

        for (int index = 0; index < numberOfRoad; index += 1) {
            int head = this.nextInt();
            int tail = this.nextInt();
            int eachweight = this.nextInt();

            edgesList.add(new Edge(head, tail, eachweight));
            edgesList.add(new Edge(tail, head, eachweight));
        }

        for (int index = 0; index < numberOfWarmwhole; index += 1) {
            int head = this.nextInt();
            int tail = this.nextInt();
            int eachweight = this.nextInt();

            edgesList.add(new Edge(head, tail, -1 * eachweight));
        }

        weight[1] = 0;

        for (int cnt = 1; cnt < numberOfState; cnt += 1) {
            for (Edge edge : edgesList) {
                if (weight[edge.head] != Integer.MAX_VALUE && weight[edge.head] + edge.weight < weight[edge.tail]) {
                    weight[edge.tail] = weight[edge.head] + edge.weight;
                }
            }
        }

        for (Edge edge : edgesList) {
            if (weight[edge.head] + edge.weight < weight[edge.tail]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}


class Edge {
    int head;
    int tail;
    int weight;

    public Edge(int head, int tail, int weight) {
        this.head = head;
        this.tail = tail;
        this.weight = weight;
    }
}