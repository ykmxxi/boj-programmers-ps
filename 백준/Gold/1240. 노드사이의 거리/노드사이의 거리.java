/*
    -
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static List<Edge>[] edges;
    static int[][] nums;
    static int[] dist;
    static boolean[] visit;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[x].add(new Edge(y, weight));
            edges[y].add(new Edge(x, weight));
        }

        nums = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            nums[i][0] = Integer.parseInt(st.nextToken());
            nums[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void dfs(int x, int depth) {
        visit[x] = true;
        dist[x] = depth;

        for (Edge e : edges[x]) {
            if (visit[e.to]) {
                continue;
            }
            visit[e.to] = true;
            dfs(e.to, depth + e.weight);
        }
    }

    static void pro() {
        for (int i = 0; i < M; i++) {
            dist = new int[N + 1];
            visit = new boolean[N + 1];

            dfs(nums[i][0], 0);

            sb.append(dist[nums[i][1]]).append('\n');
        }

        System.out.print(sb);
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

}
