import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main { // problem 1753 shortest path
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
        int numberOfVertex = this.nextInt();
        int numberOfEdge = this.nextInt();
        int start = this.nextInt() - 1;
        List<List<Point>> edges = new ArrayList<>(numberOfVertex);
        for (int index = 0; index < numberOfVertex; index += 1) {
            edges.add(new ArrayList<>());
        }

        for (int index = 0; index < numberOfEdge; index += 1) {
            int headVertex = this.nextInt() - 1;
            int tailVertex = this.nextInt() - 1;
            int weight = this.nextInt();

            List<Point> headTail = edges.get(headVertex);
            headTail.add(new Point(tailVertex, weight));
        }

        int[] weights = new int[numberOfVertex];
        boolean[] isVisted = new boolean[numberOfVertex];
        Arrays.fill(weights, -1);


        weights[start] = 0;
        for (int turn = 0; turn < numberOfVertex - 1; turn += 1) {
            int minIndex = -1;
            int minValue = -1;
            for (int index = 0; index < weights.length; index += 1) {
                if (!isVisted[index] && weights[index] != -1) {
                    if (minValue == -1 || minValue > weights[index]) {
                        minIndex = index;
                        minValue = weights[index];
                    }
                }
            }
            if (minIndex == -1) {
                break;
            }
            isVisted[minIndex] = true;

            List<Point> edge = edges.get(minIndex);
            for (Point each : edge) {
                if (!isVisted[each.x] && (weights[each.x] == -1 || weights[each.x] > weights[minIndex] + each.y)) {
                    weights[each.x] = weights[minIndex] + each.y;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int data : weights) {
            if (data == -1) {
                sb.append("INF\n");
            } else {
                sb.append(data).append("\n");
            }
        }
        System.out.println(sb.toString());
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