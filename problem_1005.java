import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private BufferedReader _bufferReader;
    private StringTokenizer _token;
    private boolean[][] _edges;
    private int _numberOfVertices;
    private Queue<Integer> _queue;

    private void setQueue(Queue<Integer> queue) {
        this._queue = queue;
    }

    public Queue<Integer> queue() {
        return this._queue;
    }

    private void setNumberOfVertices(int vertices) {
        this._numberOfVertices = vertices;
    }

    public int numberOfVertices() {
        return this._numberOfVertices;
    }

    private void setBufferReader(BufferedReader newBufferReader) {
        this._bufferReader = newBufferReader;
    }

    public BufferedReader bufferReader() {
        return this._bufferReader;
    }

    private void setToken(StringTokenizer newToken) {
        this._token = newToken;
    }

    public StringTokenizer token() {
        return this._token;
    }

    private void setEdges(boolean[][] newEdges) {
        this._edges = newEdges;
    }

    private boolean[][] edges() {
        return this._edges;
    }

    private void solve() {
        this.init();

        int howMuch = this.nextInt();
        int[] result = new int[howMuch];
        for (int i = 0; i < howMuch; i++) {
            int theNumberOfVertices = this.nextInt();
            this.setNumberOfVertices(theNumberOfVertices);
            int[] weightOfVertex = new int[theNumberOfVertices + 1];
            int theNumberOfEdges = this.nextInt();
            this.setEdges(new boolean[theNumberOfVertices + 1][theNumberOfVertices + 1]);

            for (int we = 1; we <= theNumberOfVertices; we++) {
                weightOfVertex[we] = this.nextInt();
            }

            for (int indexOfVertex = 0; indexOfVertex < theNumberOfEdges; indexOfVertex++) {
                int tailVertex = this.nextInt();
                int headVertex = this.nextInt();
                if (tailVertex <= this.numberOfVertices() && headVertex <= this.numberOfVertices()) {
                    this.edges()[tailVertex][headVertex] = true;
                } else {
                }
            }

            int finalPosition = this.nextInt();
            if ((1 <= theNumberOfVertices && theNumberOfVertices <= 1000)
                    || (1 <= theNumberOfEdges && theNumberOfEdges <= 100000)
                    || (finalPosition >= 1 && finalPosition <= this.numberOfVertices())) {
                result[i] = this.tologicalSolve(weightOfVertex, finalPosition);
            }
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    private int tologicalSolve(int[] weight, int indexOfTheFinalPosition) {
        this.setQueue(new LinkedList<Integer>());
        int[] result = new int[this.numberOfVertices() + 1];
        for (int vertex = 1; vertex <= this.numberOfVertices(); vertex++) {
            if (this.isLeaf(vertex)) {
                result[vertex] = weight[vertex];
                this.queue().add(vertex);
            }
        }

        while (!this.queue().isEmpty()) {
            int vertex = this.queue().poll();
            for (int headVertex = 1; headVertex <= this.numberOfVertices(); headVertex++) {
                if (this.edges()[vertex][headVertex]) {
                    result[headVertex] = Math.max(result[headVertex], result[vertex] + weight[headVertex]);

                    this.edges()[vertex][headVertex] = false;

                    if (this.howManyTailVertex(headVertex) == 0) {
                        this.queue().add(headVertex);
                    }
                }
            }
        }
        return result[indexOfTheFinalPosition];
    }

    private int howManyTailVertex(int headVertex) {
        int count = 0;
        for (int tailVertex = 1; tailVertex <= this.numberOfVertices(); tailVertex++) {
            if (this.edges()[tailVertex][headVertex]) {
                count++;
            }
        }
        return count;
    }

    private boolean isLeaf(int vertex) {
        for (int tailVertex = 1; tailVertex <= this.numberOfVertices(); tailVertex++) {
            if (this.edges()[tailVertex][vertex]) {
                return false;
            }
        }
        return true;
    }

    private void init() {
        this.setBufferReader(new BufferedReader(new InputStreamReader(System.in)));
        this.setToken(new StringTokenizer(""));
    }

    private String readLine() {
        try {
            return this.bufferReader().readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String nextString() {
        while (!this.token().hasMoreTokens()) {
            this.setToken(new StringTokenizer(this.readLine()));
        }
        return this.token().nextToken();
    }

    private int nextInt() {
        while (true) {
            try {
                int intNumber = Integer.parseInt(this.nextString());
                return intNumber;
            } catch (NumberFormatException e) {
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
