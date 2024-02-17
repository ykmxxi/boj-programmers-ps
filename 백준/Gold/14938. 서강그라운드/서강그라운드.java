/*
    - n: 지역의 개수, m: 수색 범위, r: 길의 개수
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m, r;
    static int[] A;
    static List<Edge>[] edges;
    static int[] dist;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        A = new int[n + 1];
        edges = new ArrayList[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++){
            A[i] = Integer.parseInt(st.nextToken());
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[x].add(new Edge(y, weight));
            edges[y].add(new Edge(x, weight));
        }

        dist = new int[n + 1];
    }

    static void dijkstra(int start) {
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (dist[info.idx] < info.dist) {
                continue;
            }

            for (Edge e: edges[info.idx]) {
                if (e.weight + dist[info.idx] >= dist[e.to]) {
                    continue;
                }

                dist[e.to] = e.weight + dist[info.idx];
                pq.add(new Info(e.to, dist[e.to]));
            }
        }

    }

    static void pro() {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            dijkstra(i);

            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) {
                    sum += A[j];
                }
            }

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
