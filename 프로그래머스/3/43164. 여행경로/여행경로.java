/*
    - ICN 공항에서 출발해 주어진 항공권을 이용해 여행경로를 짜려고 한다
    - 모든 공항은 알파벳 대문자 3글자, 공항 수는 3 ~ 10,000
    - tickets의 각 행은 a -> b (방향 그래프)
    - 주어진 항공권을 모두 사용해야 한다
*/

import java.util.*;

class Solution {
    
    Map<String, Queue<String>> adj;
    
    public String[] solution(String[][] tickets) {
        // 인접리스트 구성
        adj = new HashMap<>();
        for (String[] t : tickets) {
            adj.computeIfAbsent(t[0], val -> new PriorityQueue<>()).offer(t[1]);
        }
        
        List<String> ans = new LinkedList<>();
        dfs("ICN", ans);
        
        return ans.toArray(new String[0]);
    }
    
    public void dfs(String airport, List<String> ans) {
        while (adj.containsKey(airport) && !adj.get(airport).isEmpty()) {
            dfs(adj.get(airport).poll(), ans);
        }
        ans.add(0, airport);
    }

}
