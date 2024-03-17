/*
    - ICN 공항에서 출발해 주어진 항공권을 이용해 여행경로를 짜려고 한다
    - 모든 공항은 알파벳 대문자 3글자, 공항 수는 3 ~ 10,000
    - tickets의 각 행은 a -> b (방향 그래프)
    - 주어진 항공권을 모두 사용해야 한다
    
*/

import java.util.*;

class Solution {
    
    List<String> ans;
    boolean[] visit;
    
    void dfs(String cur, String route, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            ans.add(route);
        } else {
            for (int i = 0; i < tickets.length; i++) {
                if (cur.equals(tickets[i][0]) && !visit[i]) { // 현재 공항이 티켓의 출발점과 같으면
                    visit[i] = true; // 해당 경로 방문 처리
                    dfs(tickets[i][1], route + " " + tickets[i][1], tickets, depth + 1); // 다음 공항으로 이동
                    visit[i] = false; // 해당 경로가 끝나면 방문 처리 초기화
                }
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        ans = new ArrayList<>();
        visit = new boolean[tickets.length];
        
        dfs("ICN", "ICN", tickets, 0); // 처음 시작점은 무조건 ICN, 처음 경로도 ICN
        
        Collections.sort(ans);
        
        return ans.get(0).split(" ");
    }
    
}
