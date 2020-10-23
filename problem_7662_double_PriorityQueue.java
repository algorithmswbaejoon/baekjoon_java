import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // problem 7662 double PriorityQueue
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

    private long nextLong() {
        return Long.parseLong(this.next());
    }

    private void run() {
        int numberOfRun = this.nextInt();
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < numberOfRun; index += 1) {
            stringBuilder.append(this.result()).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private String result() {
        int numberOfInput = this.nextInt();
        boolean[] isVisited = new boolean[numberOfInput];
        Queue<Point> maxHeap = new PriorityQueue<>(Comparator.comparingLong(point -> -point.value));
        Queue<Point> minHeap = new PriorityQueue<>(Comparator.comparingLong(point -> point.value));
        for (int index = 0; index < numberOfInput; index += 1) {
            String instruction = this.next();
            long data = this.nextLong();
            if (instruction.equals("I")) {
                Point nowPoint = new Point(data, index);
                maxHeap.add(nowPoint);
                minHeap.add(nowPoint);
            } else {
                if (data == 1 && !maxHeap.isEmpty()) {
                    Point maxPoint = maxHeap.peek();
                    isVisited[maxPoint.index] = true;
                } else if (!minHeap.isEmpty()) {
                    Point minPoint = minHeap.peek();
                    isVisited[minPoint.index] = true;
                }
            }

            while (!minHeap.isEmpty() && isVisited[minHeap.peek().index]) {
                minHeap.poll();
            }

            while (!maxHeap.isEmpty() && isVisited[maxHeap.peek().index]) {
                maxHeap.poll();
            }
        }
        if (maxHeap.isEmpty() || minHeap.isEmpty()) {
            return "EMPTY";
        } else {
            return Long.toString(maxHeap.peek().value) + " " + Long.toString(minHeap.peek().value);
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

class Point {
    long value;
    int index;

    public Point(long value, int index) {
        this.value = value;
        this.index = index;
    }
}
