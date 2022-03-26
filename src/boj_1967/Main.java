package boj_1967;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 트리의 지름
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    private void destory() {
        try {
            if (this._br != null) {
                _br.close();
            }
        } catch (Exception ignored) {

        }
    }

    public void run() {
        init();
        solve();
        destory();
    }

    private void solve() {
        int numberOfEdges = nextInt();

        Map<Integer, List<Edge>> weightEdges = new HashMap<>();
        Set<Integer> nodeNumbers = new HashSet<>();


        for (int inputIndex = 0; inputIndex < numberOfEdges - 1; inputIndex++) {
            int parentNodeNumber = nextInt();
            int childNodeNumber = nextInt();
            int weight = nextInt();

            nodeNumbers.add(parentNodeNumber);
            nodeNumbers.add(childNodeNumber);

            List<Edge> parentWeightNextEdge = weightEdges.getOrDefault(parentNodeNumber, new ArrayList<>());
            List<Edge> childWeightNextEdge = weightEdges.getOrDefault(childNodeNumber, new ArrayList<>());
            parentWeightNextEdge.add(new Edge(childNodeNumber, weight));
            childWeightNextEdge.add(new Edge(parentNodeNumber, weight));

            weightEdges.put(parentNodeNumber, parentWeightNextEdge);
            weightEdges.put(childNodeNumber, childWeightNextEdge);
        }

        List<Integer> nodes = new ArrayList<>(nodeNumbers);
        if (numberOfEdges > 1){
            boolean[] isVisistedMap = new boolean[nodes.size() + 1];
            isVisistedMap[1] = true;
            Point endPoint = getLongestWegith(1, weightEdges, isVisistedMap, 0);

            isVisistedMap = new boolean[nodes.size() + 1];
            isVisistedMap[endPoint.nodeNumber] = true;
            Point longestWegith = getLongestWegith(endPoint.nodeNumber, weightEdges, isVisistedMap, 0);

            System.out.println(longestWegith.distance);
        } else {
            System.out.println(0);
        }
    }

    private Point getLongestWegith(int rootNode, Map<Integer, List<Edge>> edges, boolean[] isVisistedMap, int distance) {
        int maxLength = distance;
        int endNode = rootNode;

        List<Edge> childWeightEdge = edges.get(rootNode);
        for (Edge edge : childWeightEdge) {
            if (!isVisistedMap[edge.nodeNumber]) {
                isVisistedMap[edge.nodeNumber] = true;
                Point childMaxEdege = getLongestWegith(edge.nodeNumber, edges, isVisistedMap, distance + edge.weight);
                if (maxLength < childMaxEdege.distance) {
                    maxLength = childMaxEdege.distance;
                    endNode = childMaxEdege.nodeNumber;
                }
            }
        }

        return new Point(endNode, maxLength);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}

class Edge {
    int nodeNumber;
    int weight;

    public Edge(int nodeNumber, int weight) {
        this.nodeNumber = nodeNumber;
        this.weight = weight;
    }
}

class Point {
    int nodeNumber;
    int distance;

    public Point(int nodeNumber, int distance) {
        this.nodeNumber = nodeNumber;
        this.distance = distance;
    }
}
