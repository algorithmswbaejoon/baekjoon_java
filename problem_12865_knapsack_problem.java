import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 12865 knapsack problem
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer((Objects.requireNonNull(this.nextLine())));
        }

        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }


    private void init() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solve() {
        this.init();

        PackObject[] packObjects = new PackObject[this.nextInt()];
        int weigth = this.nextInt();
        for (int index = 0; index < packObjects.length; index++) {
            int nowWeigth = this.nextInt();
            int nowValue = this.nextInt();
            packObjects[index] = new PackObject(index, nowValue, nowWeigth);
        }
        System.out.println(this.knapsack(packObjects, weigth));

        this.end();
    }

    private int knapsack(PackObject[] packObjects, int weightData) {// 알고리즘
        int weigthNumber = weightData + 1;
        int[][] sumMinWeigth = new int[packObjects.length + 1][weigthNumber];

        for (int indexOfRow = 1; indexOfRow <= packObjects.length; indexOfRow++) {
            for (int indexOfCol = 1; indexOfCol < weigthNumber; indexOfCol++) {
                if (packObjects[indexOfRow - 1].weight > indexOfCol) { // 새로 추가된 물건의 무게가 현재 무게 보다 더 클 경우 전에 값을 가져온다
                    sumMinWeigth[indexOfRow][indexOfCol] = sumMinWeigth[indexOfRow - 1][indexOfCol];
                } else {// 추가된 물건을 넣을 수 있는 경우
                    int beforeDataResult = sumMinWeigth[indexOfRow - 1][indexOfCol]; // 현재 추가된 물건을 넣지 않고 했을 경우
                    int nextDataResult = sumMinWeigth[indexOfRow - 1][indexOfCol - packObjects[indexOfRow - 1].weight]
                            + packObjects[indexOfRow - 1].value; // 현재 물건을 넣기전에 값 + 현재 물건의 value 를 했을 때 값
                    sumMinWeigth[indexOfRow][indexOfCol] = Math.max(beforeDataResult, nextDataResult); // 큰값 체택
                } // 더 큼 value를 선택한다
            }
        }

        return sumMinWeigth[packObjects.length][weigthNumber - 1];
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}

class PackObject {
    public int itenNum;
    public int value;
    public int weight;

    public PackObject(int itenNum, int value, int weight) {
        this.itenNum = itenNum;
        this.value = value;
        this.weight = weight;
    }
}
