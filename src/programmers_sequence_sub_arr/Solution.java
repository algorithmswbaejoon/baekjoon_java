package programmers_sequence_sub_arr;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int len = elements.length;

        for (int size = 1; size <= len; size++) {
            for (int start = 0; start < len; start++) {
                int sum = 0;
                for (int movePoint = 0; movePoint < size; movePoint++) {
                    int index = (start + movePoint) % len;
                    sum += elements[index];
                }
                set.add(sum);
            }
        }

        return set.size();
    }
}