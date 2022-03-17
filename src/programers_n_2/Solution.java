package programers_n_2;

public class Solution {
    public int[] solution(int n, long left, long right) {
        int[] ans = new int[(int) (right - left + 1)];

        int index = 0;
        while (left <= right) {
            ans[index] = (Math.max((int) (left / n), (int) (left++ % n)) + 1);
            index++;
        }

        return ans;
    }
}
