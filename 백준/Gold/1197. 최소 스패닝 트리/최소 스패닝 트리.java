import java.util.*;
import java.io.*;

public class Main {
    
    static int V, E;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        pq = new PriorityQueue<>();
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            pq.offer(new Edge(v1, v2, w));
        }
        
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }
    
    static int find(int x) { // x가 속한 집합의 루트 노드 찾기
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b) {
        int p1 = parent[a];
        int p2 = parent[b];
        
        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }
    
    static void pro() {
        int answer = 0;
        
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (find(edge.v1) != find(edge.v2)) {
                union(edge.v1, edge.v2);
                answer += edge.weight;
            }
        }
        
        System.out.println(answer);
    }
    
    public static void main(String[] args) throws IOException {
        input();
        pro();
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
            return this.weight - o.weight;
        }
        
    }
    
}
