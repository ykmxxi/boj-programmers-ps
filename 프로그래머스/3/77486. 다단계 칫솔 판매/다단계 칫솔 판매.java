/*
    - 트리 형태 -> dfs
    - 칫솔의 판매에 의하여 발생하는 이익의 10%를 추천인에게 배분하고 나머지는 자신이 가짐
    - 10% 계산할 때 원 단위에서 절사, 1원 미만인 경우 자신이 모두 가짐
*/

import java.util.*;

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        Map<String, Integer> map = new HashMap<>(); // key:이름, value:수익
        Map<String, String> parent = new HashMap<>(); // key:자식의이름, value:부모의이름
        parent.put("-", "boss");
        
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
            map.put(enroll[i], 0);
        }
        
        for (int i = 0; i < seller.length; i++) {
            int cost = amount[i] * 100;
            String child = seller[i];
            
            map.put(child, map.getOrDefault(child, 0) + cost);
            
            // 추천인이 있으면
            int n = cost / 10;
            String ref = parent.get(child);
            while (n >= 1 && !child.equals("-")) {
                map.put(child, map.getOrDefault(child, 0) - n); // 추천인에게 줄 돈을 빼고
                map.put(ref, map.getOrDefault(ref, 0) + n); // 추천인에게 돈을 주고
                
                child = ref;
                ref = parent.get(child);
                n /= 10;
            }
           
        }
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]);
        }
        
        return answer;
    }
    
}
