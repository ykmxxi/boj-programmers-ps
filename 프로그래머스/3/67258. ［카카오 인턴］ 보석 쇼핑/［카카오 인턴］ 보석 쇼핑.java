/*
    - 특정 범위의 물건들을 모두 구매 (슬라이딩 윈도우?)
    - 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 구하기
*/

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        
        for (String gem : gems) {
            set.add(gem);
        }
        
        int kind = set.size();
        
        int L = 0;
        int R = 0;
        int min = Integer.MAX_VALUE;
        
        Map<String, Integer> map = new HashMap<>(); // key:gem, value:gem 구매개수
        
        while (R < gems.length) {
            map.put(gems[R], map.getOrDefault(gems[R], 0) + 1);
            
            while (map.get(gems[L]) > 1) {
                map.put(gems[L], map.get(gems[L]) - 1);
                L++;
            }
            
            if (R - L < min && map.size() == kind) {
                answer[0] = L + 1;
                answer[1] = R + 1;
                min = R - L;
            }
            R++;
        }
        
        return answer;
    }
    
}
