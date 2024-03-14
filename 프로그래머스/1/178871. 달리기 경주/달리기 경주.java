/*
    - 선수들이 자기 바로 앞의 선수를 추월할 때 추월한 선수의 이름을 부름
    - 1등 ~ 꼴등 순서대로 담긴 문자열 배열이 주어질 때 부른 이름이 주어짐, 경주가 끝났을 때 등수를 순서대로 반환
*/

import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rank = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }
        
        for (String c : callings) {
            int r = rank.get(c); // 추월한 선수의 이전 등수
            
            // 추월당한 선수 찾기
            String name = players[r - 1]; // 1등은 부르지 않으므로 r - 1 예외 처리 X
            rank.put(name, r);
            players[r] = name;
            
            // 추월한 선수 랭킹 업데이트
            rank.put(c, r - 1);
            players[r - 1] = c;
        }
        
        return players;
        
    }
    
}
