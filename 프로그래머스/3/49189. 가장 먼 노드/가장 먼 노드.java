/*
    N개의 노드가 있는 그래프, 각 노드는 1 ~ N번
    1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하는 문제 (최단경로로 이동했을 때 간선의 개수가 가장 많은 노드)
    N은 2 이상 20,000 이하
*/

import java.util.*;

class Solution {
    
    static int max = 0;
    static ArrayList<Integer>[] adj;
    static int[] dist, visit;
    
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        
        q.add(start);
        visit[start] = 1;
        dist[start] = 0;
        
        while(!q.isEmpty()) {
            int x = q.poll();
            
            for(int y : adj[x]) {
                if (visit[y] == 1) {
                    continue;
                }
                dist[y] = dist[x] + 1;
                visit[y] = 1;
                q.add(y);
                
                max = Math.max(max, dist[y]);
                
            }
        }
    
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        dist = new int[20001];
        visit = new int[20001];
        
        adj = new ArrayList[20001];
        for (int i = 0; i <= 20000; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] info : edge) {
            int x = info[0];
            int y = info[1];
            adj[x].add(y);
            adj[y].add(x);
        }
        
        bfs(1);
        
        for (int i = 0; i < 20000; i++) {
            if(dist[i] == max) {
                answer++;
            }
        }
        
        return answer;
    }
}