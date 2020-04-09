import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 2 Add Two Numbers
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
        ListNode l1 = null;
        ListNode l2 = null;
        /*
         * [2,4,3]
         *[5,6,4]
         * */
        l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println(this.addTwoNumbers(l1, l2));

        this.end();
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode firstNode = l1;
        ListNode secondNode = l2;
        int addedVal = 0;
        ListNode result = null;
        ListNode nextNode = null;
        while (firstNode != null || secondNode != null) {
            int firstVal = 0;
            int secondVal = 0;
            if (firstNode != null) {
                firstVal = firstNode.val;
                firstNode = firstNode.next;
            }
            if (secondNode != null) {
                secondVal = secondNode.val;
                secondNode = secondNode.next;
            }
            int sum = firstVal + secondVal + addedVal;
            addedVal = sum / 10;
            sum %= 10;

            if (result == null) {
                result = new ListNode(sum);
                nextNode = result;
            } else {
                nextNode.next = new ListNode(sum);
                nextNode = nextNode.next;
            }
        }
        if (addedVal > 0) {
            nextNode.next = new ListNode(addedVal);
        }

        return result;
    }


    public static void main(String[] args) {
        new Main().solve();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.val);
        ListNode nextData = this.next;
        while (nextData != null) {
            stringBuilder.append(" ").append(nextData.val);
            nextData = nextData.next;
        }
        return stringBuilder.toString();
    }
}