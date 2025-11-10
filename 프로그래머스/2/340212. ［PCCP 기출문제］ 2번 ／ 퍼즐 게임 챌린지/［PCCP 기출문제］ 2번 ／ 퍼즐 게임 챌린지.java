import java.util.*;

class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        // diff <= level time_cur 사용
        // diff > level (diff - level)번 틀리고 풀때마다 time_cur 만큼 사용
        // 틀리때마다 이전 퍼즐을 다시 풀어야 함 time_prev 시간 만큼 사용(난이도 상관 없)
        // 숙련도의 최소값 제한 시간 내 풀수 있는 -> 이분 탐색
        
        int L = 1;
        int R = 100000;
        
        while (L <= R) {
            int mid = (R - L) / 2 + L; // overflow 방지
            if (check(mid, diffs, times, limit)) { // 해당 레벨로 풀수 있으면
                answer = mid;
                R = mid - 1;
            } else { // 해당 레벨로 풀수 없으면
                L = mid + 1;
            }
        }
        return answer;
    }
    
    boolean check(int level, int[] diffs, int[] times, long limit) {
        long total = 0L;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                total += times[i];
            } else {
                long sum = times[i];
                long cnt = diffs[i] - level;
                if (i != 0) {
                    sum += times[i - 1];
                }
                total += (cnt * sum) + times[i];
            }
        }
        return total <= limit;
    }
}
