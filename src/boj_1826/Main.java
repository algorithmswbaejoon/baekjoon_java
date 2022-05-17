package boj_1826;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 연료 채우기
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
        int numberOfGasStation = nextInt();
        Queue<GasStation> gasStationsByDistance = new PriorityQueue<>(Comparator.comparingInt(o -> o.position));
        for (int i = 0; i < numberOfGasStation; i++) {
            gasStationsByDistance.add(new GasStation(nextInt(), nextInt()));
        }

        int distOfFirstPositionFromVilage = nextInt();
        int nowGasAmount = nextInt();
        int nowPosition = 0;

        Queue<GasStation> pointQueue = new PriorityQueue<>(Comparator.comparingInt(o -> -o.gas));
        int visitCount = 0;

        while (true) {
            nowPosition = nowPosition + nowGasAmount;
            nowGasAmount = 0;

            if (nowPosition >= distOfFirstPositionFromVilage) {
                break;
            }

            while (!gasStationsByDistance.isEmpty() && gasStationsByDistance.peek().position <= nowPosition) {
                pointQueue.add(gasStationsByDistance.poll());
            }

            if (pointQueue.isEmpty()) {
                System.out.println(-1);
                return;
            }

            visitCount++;
            nowGasAmount += pointQueue.poll().gas;
        }

        System.out.println(visitCount);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}

class GasStation {
    int position;
    int gas;

    public GasStation(int position, int gas) {
        this.position = position;
        this.gas = gas;
    }
}

