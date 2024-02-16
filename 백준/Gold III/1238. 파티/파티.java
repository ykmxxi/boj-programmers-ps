/*
    - N개의 마을에 각각 한 명의 학생 존재
    - X번(1 <= X <= N) 마을에 모여서 파티, M개의 단방향 간선(방향 그래프) 1 ~ 100 가중치
    - 자신이 사는 마을 ~ X 최단거리 + X ~ 자신이 사는 마을 최단거리 -> 가장 큰 값을 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = 1_000_000;
    static int N, M, X;
    static int[] dist;
    static List<Edge>[] edges;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

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
        dist = new int[N + 1];
    }

    static void dijkstra(int start) {
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start, 0));
        dist[start] = 0;

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
                pq.add(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void pro() {
        // dijkstra 돌아서 1번 학생부터 1 -> X + X -> 1 최단거리 합을 구해 최대값 갱신
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            dijkstra(i);
            sum += dist[X];
            dijkstra(X);
            sum += dist[i];

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
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
