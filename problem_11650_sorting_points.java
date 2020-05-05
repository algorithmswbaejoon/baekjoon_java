import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // problem 11650 sorting points
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
        int numberOfData = this.nextInt();
        List<Point> points = new ArrayList<>();
        for (int index = 0; index < numberOfData; index++) {
            points.add(new Point(this.nextInt(), this.nextInt()));
        }
        Collections.sort(points);
        StringBuilder stringBuilder = new StringBuilder();
        for (Point innerPoint : points) {
            stringBuilder.append(innerPoint).append("\n");
        }
        System.out.println(stringBuilder.toString());

        if (!this.end()) {
            System.out.println("program error");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x > o.x) {
            return 1;
        } else if (this.x == o.x) {
            if (this.y > o.y) {
                return 1;
            } else if (this.y == o.y) {
                return 0;
            }
            return -1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}
