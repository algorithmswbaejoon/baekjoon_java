package programmers_rollcake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int toppingNum : topping) {
            Integer count = countMap.getOrDefault(toppingNum, 0);
            countMap.put(toppingNum, count + 1);
        }

        Set<Integer> myToppingTypes = new HashSet<>();

        int result = 0;

        for (int nowToppingNum : topping) {
            myToppingTypes.add(nowToppingNum);
            int toppingCount = countMap.getOrDefault(nowToppingNum, 0) - 1;
            if (toppingCount <= 0) {
                countMap.remove(nowToppingNum);
            } else {
                countMap.put(nowToppingNum, toppingCount);
            }

            if (myToppingTypes.size() == countMap.size()) {
                result++;
            } else if (myToppingTypes.size() > countMap.size()) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1, 2, 1, 3, 1, 4, 1, 2}));
    }
}
