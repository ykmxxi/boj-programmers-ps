/*
    - 시소는 중심으로부터 2, 3, 4 거리의 지점에 좌석이 하나씩 존재
    - 두 명이 마주보고 탔을 때, 시소가 평형인 상태에서 각각에 의해 걸리는 토크의 크기가 서로 상쇄되어 균형을 이루면 시소 짝꿍
    - 탑승한 사람의 무게 * 거리 값이 양쪽이 다 같으면 짝꿍
    
*/

import java.util.*;

class Solution {
    
    public long solution(int[] weights) {
        long answer = 0L;
        
        Arrays.sort(weights);
        
        Map<Integer, Long> map = new HashMap<>(); // key:몸무게, value:사람수
        
        for (int w : weights) {
            map.put(w, map.getOrDefault(w, 0L) + 1L);
        }
        
        for (int k : map.keySet()) {
            long cnt = map.get(k);
            answer += cnt * (cnt - 1) / 2;
            
            if (k % 2 == 0 && map.containsKey(k * 3 / 2)) {
                answer += cnt * map.get(k * 3 / 2);
            }
            if (map.containsKey(2 * k)) {
                answer += cnt * map.get(2 * k);
            }
            if (k % 3 == 0 && map.containsKey(k * 4 / 3)) {
                answer += cnt * map.get(k * 4 / 3);
            }
        }
        
        return answer;
        
    }
    
}
