import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, A, B;
    static List<Edge>[] edges;
    static int[] dist;

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

    static void dijkstra(int start) {
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE; // dist 배열 초기화
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (dist[info.idx] < info.dist) { // 꺼낸 정보의 거리가 커서 필요없음
                continue;
            }

            for (Edge e :edges[info.idx]) {
                if (e.weight + info.dist >= dist[e.to]) { // 새로운 정보의 거리가 더 길다면 필요없음
                    continue;
                }
                dist[e.to] = e.weight + info.dist;
                pq.offer(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void pro() {
        dijkstra(A);

        System.out.println(dist[B]);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
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
        int idx; // 도착 idx
        int dist; // 거리

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
