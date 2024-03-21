/*
    - n: 정점의 개수(1 ~ n번), s: 출발지점, a: A의 도착지점, b: B의 도착지점, fares[]: 두 정점과 가중치
    - 양방향 그래프
    - 최악의 경우: 모든 간선의 요금이 100,000(10^5) * 200(199) / 2 (20,000) = 2 * 10^9 (int 범위)
    1. 시작점 s 출발 모든 정점까지의 최소 비용 구하기
    2. 시작점을 a, b로 각각 설정해 모든 정점까지의 최소 비용 구하기
    3. 1 ~ n 까지 같이 탑승한다 가정하고 반복문을 돌며 together[i] + distA[i] + distB[i] 하면서 최소값 구하기
*/

import java.util.*;

class Solution {
    
    List<Edge>[] edges;
    
    int[] dijkstra(int s, int n, int[] dist) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(s, 0));
        dist[s] = 0;
        
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            
            if (info.dist > dist[info.idx]) { // 꺼낸 정보의 거리가 더 커서 쓸모가 없으면
                continue;
            }
            
            for (Edge e : edges[info.idx]) {
                if (e.weight + info.dist > dist[e.to]) {
                    continue;
                }
                dist[e.to] = e.weight + info.dist;
                pq.offer(new Info(e.to, dist[e.to]));
            }
        }
        
        return dist;
    }
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int w = fare[2];
            
            edges[from].add(new Edge(to, w));
            edges[to].add(new Edge(from, w));
        }
        
        // 1. 먼저 다익스트라로 최소거리 구하기
        int[] together = new int[n + 1];
        int[] distA = new int[n + 1];
        int[] distB = new int[n + 1];
        
        together = dijkstra(s, n, together);
        distA = dijkstra(a, n, distA);
        distB = dijkstra(b, n, distB);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (together[i] == Integer.MAX_VALUE || distA[i] == Integer.MAX_VALUE || distB[i] == Integer.MAX_VALUE) {
                continue;
            }
            answer = Math.min(together[i] + distA[i] + distB[i], answer);
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
            return this.dist - o.dist;
        }
    }
    
}
