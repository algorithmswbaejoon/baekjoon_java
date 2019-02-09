import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private int[][] _edges;
    private int[] _weightOfvertex;
    private int _numberOfVertices;
    private Queue<Integer> _queue;
    private int _sum;

    private void setSum(int sum) {
        this._sum = sum;
    }

    public int sum() {
        return _sum;
    }

    private void setQueue(Queue<Integer> queue) {
        this._queue = queue;
    }

    public Queue<Integer> queue() {
        return this._queue;
    }

    private void setNumberOfVertices(int vertex) {
        this._numberOfVertices = vertex;
    }

    public int numberOfVertices() {
        return this._numberOfVertices;
    }

    private void setEdge(int[][] edges) {
        this._edges = edges;
    }

    public int[][] edges() {
        return this._edges;
    }

    private void setWeightOfVertex(int[] array) {
        this._weightOfvertex = array;
    }

    public int[] weightOfVertex() {
        return this._weightOfvertex;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner input = new Scanner(System.in);
        int howMany = input.nextInt();

        int[] sumsOfTheCraft = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            String deletebuffer = input.nextLine();
            String vertexAndEdge = input.nextLine();
            String[] str = main.splitString(vertexAndEdge);
            int firstNum = Integer.parseInt(str[0]);
            main.setNumberOfVertices(firstNum);
            int secondNum = Integer.parseInt(str[1]);
            if (firstNum < 1 || firstNum > 1000) {
                return;
            }
            if (secondNum < 1 || secondNum > 100000) {
                return;
            }

            main.setEdge(new int[firstNum][firstNum]);

            String weightOfEdge = input.nextLine();
            str = main.splitString(weightOfEdge);
            if (str.length != firstNum) {
                return;
            }
            main.setWeightOfVertex(new int[str.length]);
            for (int index = 0; index < str.length; index++) {
                main.weightOfVertex()[index] = Integer.parseInt(str[index]);
                if (main.weightOfVertex()[index] < 0 || main.weightOfVertex()[index] >= 100000) {
                    return;
                }
            }

            for (int index = 0; index < secondNum; index++) {
                String edgeOfTheVertex = input.nextLine();
                str = main.splitString(edgeOfTheVertex);
                int tailVertex = Integer.parseInt(str[0]);
                int headVertex = Integer.parseInt(str[1]);
                if (!main.addEdge(tailVertex, headVertex)) {
                    return;
                }
            }

            int finalPosition = input.nextInt();
            if (main.edgeIsValid(finalPosition)) {
                sumsOfTheCraft[i] = main.solve(finalPosition);
            } else {
                return;
            }
        }
        for (int i = 0; i < sumsOfTheCraft.length; i++) {
            System.out.println(sumsOfTheCraft[i]);
        }
    }

    public String[] splitString(String str) {
        return str.split(" ");
    }

    public boolean EdgeDoesExist(int tailVertex, int headVertex) {
        return this.edges()[tailVertex - 1][headVertex - 1] == 1;
    }

    public boolean addEdge(int tailVertex, int headVertex) {
        if (this.edgeIsValid(tailVertex) && this.edgeIsValid(headVertex)
                && !this.EdgeDoesExist(tailVertex, headVertex)) {
            this.edges()[tailVertex - 1][headVertex - 1] = 1;
            return true;
        }
        return false;
    }

    public boolean edgeIsValid(int vertex) {
        return this.numberOfVertices() >= vertex && 1 <= vertex;
    }

    public int solve(int finalPosition) {
        int max = 0;
        this.setSum(this.weightOfVertex()[finalPosition - 1]);
        this.setQueue(new LinkedList<Integer>());
        this.sumOfTheWeights(finalPosition);
        this.setSum(0);
        while (!this.queue().isEmpty()) {
            int temp = this.queue().remove();
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }

    public void sumOfTheWeights(int finalPosition) {
        int leaf = finalPosition;
        int temp;
        if (this.isLeaf(leaf)) {
            temp = this.weightOfVertex()[leaf - 1];
            this.queue().add(this.sum());
            return;
        }
        int[] tailVertexes = this.findTailVertex(leaf);
        for (int index = 0; index < tailVertexes.length; index++) {
            temp = this.weightOfVertex()[tailVertexes[index] - 1];
            this.setSum(this.sum() + temp);
            this.sumOfTheWeights(tailVertexes[index]);
            this.setSum(this.sum() - temp);
        }
    }

    public boolean isLeaf(int headVertex) {
        for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
            if (this.edges()[tailVertex][headVertex - 1] == 1) {
                return false;
            }
        }
        return true;
    }

    public int[] findTailVertex(int headVertex) {
        int count = 0;
        for (int tailVertex = 1; tailVertex <= this.numberOfVertices(); tailVertex++) {
            if (this.EdgeDoesExist(tailVertex, headVertex)) {
                count++;
            }
        }
        if (count == 0) {
            return null;
        }
        int[] tailVertexes = new int[count];
        int index = 0;
        for (int tailVertex = 1; tailVertex <= this.numberOfVertices(); tailVertex++) {
            if (this.EdgeDoesExist(tailVertex, headVertex)) {
                tailVertexes[index++] = tailVertex;
            }
        }
        return tailVertexes;
    }
}
