/*
    https://school.programmers.co.kr/learn/courses/30/lessons/42578
    
    각 종류별로 최대 1가지만 착용 가능
    착용한 의상의 일부가 겹치더라도, 다른 의상이 겹치지 않거나, 의상을 추가로 더 착용한 경우 서로 다른 방법
    하루에 최소 한 개의 의상은 입는다
    
    2차원 배열로 옷 정보가 주어질 때 서로 다른 옷의 조합의 수
*/

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        // 먼저 의상의 개수를 카테고리 별로 분류
        Map<String, Integer> map = new HashMap<>();
        for (String[] info : clothes) {
            String type = info[1];
            
            map.put(type, map.getOrDefault(type, 1) + 1);
        }
        
        for (String key : map.keySet()) {
            answer *= map.get(key);
            System.out.println(map.get(key));
        }
        
        
        return answer - 1;
    }
}