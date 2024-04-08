/*
    - 컴퓨터와 컴퓨터(정점)를 모두 연결, 모든 컴퓨터가 연결이 되어 있어야 한다
    - 컴퓨터를 연결하는 비용을 최소 -> 최소 신장 트리
    - N(컴퓨터 수, 정점의 수, 1 ~ 1000), M(1 ~ 100,000, 간선의 수)
    - 크루스칼 or 프림
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, w));
        }
    }

    static int find(int x) {
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
        int ans = 0;

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.v1) != find(cur.v2)) {
                union(cur.v1, cur.v2);
                ans += cur.weight;
            }
        }

        System.out.println(ans);
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
