/*
- dp[time][temperature]는 time분에 해당 실내온도가 되기 위한 최소 소비전력
- 매 분마다 에어컨을 끄는 경우, 현재 온도를 유지하는 경우, 온도를 1도 높이거나 낮추는 경우를 다음 상태로 전이
- 승객이 탑승한 시간에는 실내온도가 반드시 t1 이상 t2 이하가 되도록 제한한다
- 시간 복잡도: O(N × T)
- 공간 복잡도: O(N × T)
*/

import java.util.*;

class Solution {

    private static final int MIN = -10;
    private static final int MAX = 40;
    private static final int TEMP = MAX - MIN + 1;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int timeLength = onboard.length;

        // dp[time][temperature + OFFSET]
        int[][] dp = new int[timeLength][TEMP];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // 0분의 실내온도는 실외온도
        dp[0][toIndex(temperature)] = 0;

        for (int time = 0; time < timeLength - 1; time++) {
            for (int current = MIN; current <= MAX; current++) {

                int currentCost = dp[time][toIndex(current)];
                if (currentCost == Integer.MAX_VALUE) {
                    continue;
                }

                // 에어컨 끄기 실내온도가 실외온도 방향으로 1도 이동
                int nextWhenOff = moveTowardOutside(current, temperature);

                update(dp, time + 1, nextWhenOff, currentCost, onboard, t1, t2);

                
                 // 에어컨을 켜고 현재 온도를 유지 희망온도 = 현재 실내온도
                update(dp, time + 1, current, currentCost + b, onboard, t1, t2);

                 // 에어컨을 켜고 온도를 1도 낮춘다
                if (current > MIN) {
                    update(dp, time + 1, current - 1, currentCost + a, onboard, t1, t2);
                }


                // 에어컨을 켜고 온도를 1도 높인다. 
                if (current < MAX) {
                    update(dp, time + 1, current + 1, currentCost + a, onboard, t1, t2);
                }
            }
        }

        return Arrays.stream(dp[timeLength - 1])
                .min()
                .orElseThrow();
    }

    private int moveTowardOutside(int current, int outsideTemperature) {
        if (current < outsideTemperature) {
            return current + 1;
        }

        if (current > outsideTemperature) {
            return current - 1;
        }

        return current;
    }

    private void update(
            int[][] dp,
            int nextTime,
            int nextTemperature,
            int nextCost,
            int[] onboard,
            int t1,
            int t2
    ) {
        if (!isValidTemperature(
                nextTime,
                nextTemperature,
                onboard,
                t1,
                t2
        )) {
            return;
        }

        int index = toIndex(nextTemperature);

        dp[nextTime][index] = Math.min(
                dp[nextTime][index],
                nextCost
        );
    }

    private boolean isValidTemperature(
            int time,
            int temperature,
            int[] onboard,
            int t1,
            int t2
    ) {
        if (onboard[time] == 0) {
            return true;
        }

        return t1 <= temperature && temperature <= t2;
    }

    private int toIndex(int temperature) {
        return temperature + 10;
    }
}
