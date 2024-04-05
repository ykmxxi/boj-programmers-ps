/*
    - 그래프가 주어질 때 MST 구하기(모든 정점들을 연결하는 부분 그래프 중 가중치 합이 최소인 트리)
    - V(1 ~ 10,000), E(1 ~ 100,000)
    - 가중치의 절대값은 1,000,000 이하(-1,000,000 ~ 1,000,000)
    - MST 가중치는 int 범위로만 이루어지게 주어짐
*/

import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        parent = new int[V + 1]; // 모든 정점의 부모 노드
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, w));
        }
    }

    static void pro() {
        int answer = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.v1) != find(edge.v2)) { // 간선의 두 정점의 부모가 다르면 사이클 X
                union(edge.v1, edge.v2);
                answer += edge.weight;
            }
        }

        System.out.println(answer);
    }

    static int find(int v) { // 부모 노드 찾기
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
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
