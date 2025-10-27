import java.util.*;
import java.io.*;

// 시간 복잡도:
// 공간 복잡도: 

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, A, B;
    static List<Edge>[] edges;
    static int[] dist;

     public static void main(String[] args) throws IOException {
        input();

        pro();
    }
    
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            edges[from].add(new Edge(to, weight));
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());   
    }

    static void pro() {
        // N개의 도시, M개의 버스(가중치 간선) -> 시작점, 도착점 주어지고 최소 비용(최소 가중치)
        dijkstra();
        
        System.out.println(dist[B]);
    }
    
    static void dijkstra() {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(A, 0));
        dist[A] = 0;
        
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            
            if (info.dist > dist[info.idx]) { // 현재 정보가 이미 갱신된 거리보다 길면 넘어가
                continue;
            }
            
            for (Edge e : edges[info.idx]) {
                if (e.weight + info.dist >= dist[e.to]) { // 새로 구한 거리가 저장된 최소 거리보다 작지 않으면 넘어가
                    continue;
                }
                dist[e.to] = e.weight + info.dist;
                pq.offer(new Info(e.to, dist[e.to]));
            }
        }
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
