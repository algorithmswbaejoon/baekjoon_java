package boj_1058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 친구
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

    private static final char KNOWN = 'Y';

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
        int[] secondFrind = new int[numberOfHuman];
        for (int i = 0; i < numberOfHuman; i++) {
            for (int j = i + 1; j < numberOfHuman; j++) {
                Set<Integer> myKnownFriends = knownFriend.get(i);
                if (myKnownFriends.contains(j)) {
                    secondFrind[i]++;
                    secondFrind[j]++;
                    continue;
                }

                Set<Integer> otherKnownFriends = knownFriend.get(j);
                for (int knownNumber : myKnownFriends) {
                    if (otherKnownFriends.contains(knownNumber)) {
                        secondFrind[i]++;
                        secondFrind[j]++;
                        break;
                    }
                }
            }
        }

        int maxFriends = 0;
        for (int i = 0; i < numberOfHuman; i++) {
            maxFriends = Math.max(maxFriends, secondFrind[i]);
        }

        System.out.println(maxFriends);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}