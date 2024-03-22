/*
    - 다익스트라 -> 거리가 K 이하인 곳 cnt 세기
*/

import java.util.*;

class Solution {
    
    static final int MAX = Integer.MAX_VALUE;
    List<Edge>[] edges;
    int[] dist;
    
    void dijkstra(int N) {
        Arrays.fill(dist, MAX);
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            
            if (info.dist > dist[info.idx]) {
                continue;
            }
            
            for (Edge e : edges[info.idx]) {
                if (e.weight + info.dist >= dist[e.to]) {
                    continue;
                }
                dist[e.to] = e.weight + info.dist;
                pq.offer(new Info(e.to, dist[e.to]));
            }
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int w = r[2];
            
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
        
        dist = new int[N + 1];
        dijkstra(N);
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
    
    static class Edge {
        int to;
        int weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Info implements Comparable<Info> {
        int idx;
        int dist;
        
        Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Info o) {
            return dist - o.dist;
        }
    }
}
