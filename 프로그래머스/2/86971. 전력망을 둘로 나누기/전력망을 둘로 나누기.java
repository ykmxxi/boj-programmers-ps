/*
    - n개의 송전탑이 전선(간선)을 통해 트리 형태로 연결
    - 간선 중 하나를 끊어 2개로 분할할려고 하고, 두 전력망이 갖는 송전탑의 개수를 최대한 비슷하게
*/

import java.util.*;

class Solution {
    
    List<Integer>[] adj;
    boolean[] visit;
    
    int dfs(int x) {
        int cnt = 1;
        visit[x] = true;
        
        for (int y : adj[x]) {
            if (visit[y]) {
                continue;
            }
            cnt += dfs(y);
        }
        
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        adj = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        for (int[] wire : wires) {
            visit = new boolean[n + 1];
            visit[wire[1]] = true;
            
            int cnt1 = dfs(wire[0]);
            int cnt2 = n - cnt1;
            
            answer = Math.min(answer, Math.abs(cnt1 - cnt2));
        }
        
        return answer;
    }
    
}
