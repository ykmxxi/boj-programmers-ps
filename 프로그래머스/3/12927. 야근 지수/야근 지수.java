/*
    - 야근 피로도 = 야근 시작 시점 + (남은 일의 작업량)^2
    - N시간 동안 야근 피로도를 최소화하도록
    - 1시간 동안 1만큼 처리
    - 제곱근을 더하기 때문에 먼저 가장 큰 수를 작게 만들어야 한다
*/

import java.util.*;

class Solution {
    
    
    public long solution(int n, int[] works) {
        long answer = 0L;
        long sum = 0L;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            sum += work;
            pq.offer(work);
        }
        
        if (sum <= n) {
            return answer;
        }
        
        for (int i = 0; i < n; i++) {
            int max = pq.poll();
            pq.offer(max - 1);
        }
        
        while (!pq.isEmpty()) {
            answer += (long) (Math.pow(pq.poll(), 2));
        }
        return answer;
    }
}