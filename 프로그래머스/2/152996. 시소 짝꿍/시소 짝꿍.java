/*
    - 시소는 중심으로부터 2, 3, 4 거리의 지점에 좌석이 하나씩 존재
    - 두 명이 마주보고 탔을 때, 시소가 평형인 상태에서 각각에 의해 걸리는 토크의 크기가 서로 상쇄되어 균형을 이루면 시소 짝꿍
    - 탑승한 사람의 무게 * 거리 값이 양쪽이 다 같으면 짝꿍
    
*/

import java.util.*;

class Solution {
    
    long comb(long a, long b) {
        if (a == b || b == 0) {
            return 1;
        }
        return comb(a - 1, b - 1) + comb(a - 1, b);
    }
    
    long calc(Map<Integer, Integer> map, int curCnt, List<Integer> target) {
        long val = 0L;
        
        for (int t : target) {
            if (map.containsKey(t)) {
                val += map.get(t) * curCnt;
            }
        }
        
        // 자기 자신 curCnt 중 2개를 뽑기
        if (curCnt > 1) {
            val += comb(curCnt, 2);
        }
        return val;
    }
    
    public long solution(int[] weights) {
        long answer = 0L;
        
        Arrays.sort(weights);
        
        Map<Integer, Integer> map = new HashMap<>(); // key:몸무게, value:사람수
        
        for (int w : weights) {
            if (map.containsKey(w)) {
                answer += map.get(w);
            }
            if (w % 3 == 0 && map.containsKey(w * 2 / 3)) {
                answer += map.get(w * 2 / 3);
            }
            if (w % 2 == 0 && map.containsKey(w / 2)) {
                answer += map.get(w / 2);
            }
            if (w % 4 == 0 && map.containsKey(w * 3 / 4)) {
                answer += map.get(w * 3 / 4);
            }
            
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        
        return answer;
    }
    
}
