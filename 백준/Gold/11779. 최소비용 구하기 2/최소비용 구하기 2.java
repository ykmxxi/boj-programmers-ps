/*
    - N 개의 도시 (~ 1000), M개의 버스(1 ~ 100,000)
    - A -> B 도시 까지 최소비용과 경로 출력
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, A, B;
    static int[] dist, before;
    static List<Edge>[] edges;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

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
        before = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start, 0));
        dist[start] = 0;
        before[start] = -1;

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (dist[info.idx] < info.dist) {
                continue;
            }

            for (Edge e : edges[info.idx]) {
                if (e.weight + dist[info.idx] >= dist[e.to]) {
                    continue;
                }
                dist[e.to] = e.weight + dist[info.idx];
                before[e.to] = info.idx;
                pq.add(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void pro() {
        dijkstra(A);

        StringBuilder sb = new StringBuilder();
        sb.append(dist[B]).append('\n');

        Deque<Integer> dq = new ArrayDeque<>();

        int n = B;
        while (n != -1) {
            dq.push(n);
            n = before[n];
        }

        sb.append(dq.size()).append('\n');
        while (!dq.isEmpty()) {
            sb.append(dq.pop()).append(' ');
        }
        System.out.println(sb);
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
