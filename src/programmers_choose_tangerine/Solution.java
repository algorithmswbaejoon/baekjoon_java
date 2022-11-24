package programmers_choose_tangerine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> tangerineCountMap = new HashMap<>();

        for (int nowTangerine : tangerine) {
            Integer count = tangerineCountMap.getOrDefault(nowTangerine, 0) + 1;
            tangerineCountMap.put(nowTangerine, count);
        }
        List<Map.Entry<Integer, Integer>> collect = tangerineCountMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .collect(Collectors.toList());

        int type = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> each : collect) {
            count += each.getValue();
            type++;
            if (count >= k) {
                return type;
            }
        }

        return type;
    }
}
