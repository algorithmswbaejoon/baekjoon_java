import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // 11505 구간 곱 구하기
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;
    private static final int MOD = 1000000007;

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

    private long nextLong() {
        return Long.parseLong(this.next());
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
        int numberOfChange = this.nextInt();
        int numberOfMult = this.nextInt();
        long[] numbers = new long[numberOfData + 1];

        int treeHeight = (int) Math.ceil(Math.log(numberOfData) / Math.log(2));
        long[] tree = new long[1 << (treeHeight + 1)];
        // make tree

        for (int index = 1; index <= numberOfData; index += 1) {
            numbers[index] = this.nextLong();
        }
        this.init(tree, numbers, 1, numberOfData, 1);

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < numberOfChange + numberOfMult; index += 1) {
            int flag = this.nextInt();
            int changeIndex = this.nextInt();
            long changeData = this.nextLong();
            if (flag == 1) {
                numbers[changeIndex] = changeData;
                update(tree, 1, numberOfData, 1, changeIndex, changeData);
            } else {
                stringBuilder.append(this.mult(tree, 1, numberOfData, 1, changeIndex, changeData)).append("\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * @param tree      : segment tree 합
     * @param numbers   : 입력받은 수를 저장
     * @param left      : 제일 시작 index
     * @param right     : 마지막 index
     * @param nodeIndex : 현재 노드 위치
     * @return : 현재 노드 값을 반환
     */
    private long init(long[] tree, long[] numbers, int left, int right, int nodeIndex) {
        if (left == right) {
            tree[nodeIndex] = numbers[left];
            return tree[nodeIndex];
        }

        int midIndex = (left + right) / 2;

        tree[nodeIndex] = (this.init(tree, numbers, left, midIndex, nodeIndex * 2) *
                this.init(tree, numbers, midIndex + 1, right, nodeIndex * 2 + 1)) % MOD;

        return tree[nodeIndex];
    }

    private long update(long[] tree, int left, int right, int nodeIndex, int changedIndex, long chagnedData) {
        // 범위 밖에 있는 경우
        if (changedIndex < left || changedIndex > right) {
            return tree[nodeIndex];
        } // 합인 경우

        if (left == right) {
            tree[nodeIndex] = chagnedData;
            return chagnedData;
        }

        int midIndex = (left + right) / 2;

        tree[nodeIndex] = (update(tree, left, midIndex, nodeIndex * 2, changedIndex, chagnedData) *
                update(tree, midIndex + 1, right, nodeIndex * 2 + 1, changedIndex, chagnedData)) % MOD;
        return tree[nodeIndex];
    }

    /**
     * @param tree        : 곱의 결과가 자정되어있는 곳
     * @param left        : index 범위
     * @param right       : index 범위 끝
     * @param nodeIndex   : 현재 노드위치 -> 2진트리 생각하기
     * @param periodStart : 구간의 제일 왼쪽
     * @param periodEnd   : 구간의 제일 오른쪽
     * @return : 구간의 곱의 결과 반환
     */
    private long mult(long[] tree, int left, int right, int nodeIndex, int periodStart, long periodEnd) {
        if (periodStart > right || periodEnd < left) {
            return 1;
        } // 범위 밖의 값들

        // 현재 구간이 범위 내부에있는 경우 값을 반환
        if (periodStart <= left && right <= periodEnd) {
            return tree[nodeIndex];
        }

        int mid = (left + right) / 2;
        //구간의 값을 곱함
        return (mult(tree, left, mid, 2 * nodeIndex, periodStart, periodEnd) * mult(tree, mid + 1, right, 2 * nodeIndex + 1, periodStart, periodEnd)) % MOD;
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
