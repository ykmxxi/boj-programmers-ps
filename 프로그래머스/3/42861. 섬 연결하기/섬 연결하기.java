/*
    - n개의 섬 사이에 다리를 건설의 비용(간선과 가중치), 모든 섬이 서로 통행 가능(모든 정점을 잇기) 필요한 최소 비용
    - MST: 크루스칼 or 프림
*/

import java.util.*;

class Solution {
    
    int[] parent;
    
    int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b) {
        int p1 = parent[a];
        int p2 = parent[b];
        
        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int[] c : costs) {
            pq.offer(new Edge(c[0], c[1], c[2]));
        }
        
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (find(edge.v1) != find(edge.v2)) { // 부모가 서로 다르면 사이클이 아님 -> 사이클로 포함
                union(edge.v1, edge.v2);
                answer += edge.weight;
            }
        }
        
        return answer;
    }
    
    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;
        
        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }
    
}
