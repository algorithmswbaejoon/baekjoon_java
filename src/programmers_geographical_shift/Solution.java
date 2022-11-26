package programmers_geographical_shift;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    private static int[] DIFF_ROW = {0, 0, -1, 1};
    private static int[] DIFF_COL = {1, -1, 0, 0};

    public int solution(int[][] land, int height) {
        int answer = 0;
        int lanSize = land.length;
        boolean[][] visitied = new boolean[lanSize][lanSize]; // 방문 여부 설정
        visitied[0][0] = true;

        Queue<int[]> q = new LinkedList<>(); // 최소 비용을 탐색하지 않은 것들
        q.add(new int[]{0, 0});

        Queue<int[]> dq = new LinkedList<>(); // 방문 + 주변 탐색
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0])); // cost, nextRow, nextCol


        while (!q.isEmpty() && dq.size() <= lanSize * lanSize) {
            while (!q.isEmpty()) {
                int[] nowPoint = q.poll();
                int row = nowPoint[0];
                int col = nowPoint[1];

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + DIFF_ROW[i];
                    int nextCol = col + DIFF_COL[i];

                    if (0 <= nextRow && nextRow < lanSize && 0 <= nextCol && nextCol < lanSize && !visitied[nextRow][nextCol]) {
                        int nowCost = Math.abs(land[row][col] - land[nextRow][nextCol]); // 현재 위치 - 다음 위치 cost 구하기
                        if (nowCost <= height) {
                            nowCost = 0;
                        }

                        if (nowCost == 0) {
                            // visit
                            q.add(new int[]{nextRow, nextCol});
                            visitied[nextRow][nextCol] = true;
                        } else {
                            // add queue for checking min
                            pq.add(new int[]{nowCost, nextRow, nextCol});
                        }
                    }
                }

                dq.add(nowPoint);
            }

            // q is empty -> bfs 종료
            // 새로운 시작점 검색
            while (!pq.isEmpty()) {
                int t[] = pq.poll();

                if (visitied[t[1]][t[2]]) {
                    // 방문 기록있음
                    continue;
                }

                answer += t[0];
                q.add(new int[]{t[1], t[2]});
                visitied[t[1]][t[2]] = true;
                // 검색 완료
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                        new int[][]{
                                {1, 4, 8, 10},
                                {5, 5, 5, 5},
                                {10, 10, 10, 10},
                                {10, 10, 10, 20}
                        },
                        3
                )
        );
    }
}