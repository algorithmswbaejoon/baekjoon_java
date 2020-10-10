import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 9576 divide book
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
        int numberOfCase = this.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < numberOfCase; index += 1) {
            sb.append(each()).append("\n");
        }

        System.out.println(sb.toString());
    }

    private int each() {
        int numberOfBook = this.nextInt();
        int numberOfPerson = this.nextInt();
        List<List<Integer>> edgesList = new ArrayList<>(numberOfPerson); // 간선 정보
        int[] matchedBook = new int[numberOfBook];
        Arrays.fill(matchedBook, -1);
        for (int index = 0; index < numberOfPerson; index += 1) {
            edgesList.add(new ArrayList<>(numberOfBook));
        }

        for (int index = 0; index < numberOfPerson; index += 1) {
            int start = this.nextInt() - 1;
            int end = this.nextInt();
            List<Integer> eachEdgesList = edgesList.get(index);
            for (int eachIndex = start; eachIndex < end; eachIndex += 1) {
                eachEdgesList.add(eachIndex);
            }
        }


        int count = 0;
        for (int personIndex = 0; personIndex < numberOfPerson; personIndex += 1) {
            boolean[] isVisited = new boolean[numberOfBook];
            if (this.dfs(personIndex, edgesList, isVisited, matchedBook)) {
                count += 1;
            }
        }

        return count;
    }

    // matching 성공 -> true, 실패 -> false
    private boolean dfs(int personId, List<List<Integer>> edges, boolean[] isVisited, int[] matchedBook) {
        List<Integer> edgeList = edges.get(personId);
        for (int bookIndex : edgeList) {
            // 이미 처리한 노드
            if (isVisited[bookIndex]) continue;
            isVisited[bookIndex] = true;
            // matchedBook[bookIndex] 현재 책과 매칭 되어있는 사람의 ID를 의미함
            if (matchedBook[bookIndex] == -1 || this.dfs(matchedBook[bookIndex], edges, isVisited, matchedBook)) {
                matchedBook[bookIndex] = personId;
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
