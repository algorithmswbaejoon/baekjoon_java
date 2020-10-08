import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main { // problem 1916 min cost
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    private void end() throws IOException {
        this.bufferedReader.close();
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

    private void run() {
        int city = this.nextInt();
        int bus = this.nextInt();

        List<List<Point>> edges = new ArrayList<>(city);
        for (int index = 0; index < city; index += 1) {
            edges.add(new ArrayList<>());
        }

        for (int index = 0; index < bus; index += 1) {
            int headVertex = this.nextInt() - 1;
            int tailVertex = this.nextInt() - 1;
            int weight = this.nextInt();

            List<Point> headTail = edges.get(headVertex);
            headTail.add(new Point(tailVertex, weight));
        }

        int[] cost = new int[city];
        Arrays.fill(cost, -1);
        Queue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(point -> point.y));
        int startCity = this.nextInt() - 1;
        int endCity = this.nextInt() - 1;
        cost[startCity] = 0;
        queue.add(new Point(startCity, 0));

        while (!queue.isEmpty()) {
            Point nowPoint = queue.poll();
            if (cost[nowPoint.x] < nowPoint.y) {
                continue;
            }
            for (Point edge : edges.get(nowPoint.x)) {
                int tail = edge.x;
                int weight = edge.y;
                int sum = cost[nowPoint.x] + weight;
                if (cost[tail] == -1 || cost[tail] > sum) {
                    cost[tail] = sum;
                    queue.add(new Point(tail, sum));
                }
            }
        }
        System.out.println(cost[endCity]);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}