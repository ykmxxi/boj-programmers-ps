/*
    - 방향성 없는 그래프, 1번 정점 -> N번 정점 최단 거리 이동
    - 임의로 주어진 두 정점은 반드시 통과, 한번 이동했던 간선 다시 이동 가능 -> 필요 X
    - 최악의 경우: 800개 정점, 모두 가중치가 1,000 -> 8 * 10^5 (int)
 */

import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 200000000;
    static int N, E, v1, v2;
    static int[] dist;
    static List<Edge>[] edges;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine(), " ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    static void dijkstra(int start) {
        dist = new int[N + 1];
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
                if (e.weight + info.dist >= dist[e.to]) {
                    continue;
                }
                dist[e.to] = e.weight + info.dist;
                pq.add(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void pro() {
        // 다익스트라로 1 ~ v1 최단 거리 + v1 - v2 간선 가중치 + v2 ~ N 최단 거리
        int ans1 = 0;

        dijkstra(1);
        ans1 += dist[v1];
        dijkstra(v1);
        ans1 += dist[v2];
        dijkstra(v2);
        ans1 += dist[N];

        int ans2 = 0;

        dijkstra(1);
        ans2 += dist[v2];
        dijkstra(v2);
        ans2 += dist[v1];
        dijkstra(v1);
        ans2 += dist[N];

        if (ans1 >= INF && ans2 >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(ans1, ans2));
        }

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
