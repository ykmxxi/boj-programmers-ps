/*
    - 정점(부대가 위치한 지역), 간선의 가중치는 모두 1
    - 최단시간에 부대로 복귀(최소 값)
*/

import java.util.*;

class Solution {
    
    List<Integer>[] adj;
    
    void bfs(int start, int[] dist) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;
        
        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (int y : adj[x]) {
                if (dist[y] != 0) {
                    continue;
                }
                dist[y] = dist[x] + 1;
                q.offer(y);
            }
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }
        
        int[] dist = new int[n + 1];
        bfs(destination, dist);
        
        for (int i = 0; i < sources.length; i++) {
            if (sources[i] == destination) {
                answer[i] = 0;
            } else {
                answer[i] = dist[sources[i]] == 0 ? -1 : dist[sources[i]];
            }
        }
        
        return answer;
    }
    
}
