package boj_1058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MainOther {
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
        int numberOfHuman = nextInt();
        List<Set<Integer>> knownFriend = new ArrayList<>(numberOfHuman);
        for (int i = 0; i < numberOfHuman; i++) {
            String line = nextLine();
            Set<Integer> knowFriendSet = new HashSet<>();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'Y') {
                    knowFriendSet.add(j);
                }
            }

            knownFriend.add(knowFriendSet);
        }

        int maxFriends = 0;
        for (int i = 0; i < numberOfHuman; i++) {
            maxFriends = Math.max(maxFriends, findLargestFriendBfs(i, numberOfHuman, knownFriend));
        }

        System.out.println(maxFriends);
    }

    private int findLargestFriendBfs(int nowFriendIndex, int numberOfHumans, List<Set<Integer>> knownFriend) {
        int[] distanceOfFriend = new int[numberOfHumans];
        Arrays.fill(distanceOfFriend, -1);
        distanceOfFriend[nowFriendIndex] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(nowFriendIndex);

        while (queue.size() > 0) {
            int friendIndex = queue.poll();
            Set<Integer> nextFriends = knownFriend.get(friendIndex);
            for (int connectedFriend : nextFriends) {
                if (distanceOfFriend[connectedFriend] < 0) {
                    distanceOfFriend[connectedFriend] = distanceOfFriend[friendIndex] + 1;
                    queue.add(connectedFriend);
                }
            }
        }

        int count = 0;
        for (int distance : distanceOfFriend) {
            if (1 <= distance && distance <= 2) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        MainOther main = new MainOther();
        main.run();
    }
}