// DFS와 BFS: https://www.acmicpc.net/problem/1260
// 시간 복잡도: 정렬 -> O(M logN), dfs -> O(N + M) , bfs -> O(N + M)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, V;
    static boolean[] visit;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1]; // 1 ~ N 정점
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
    }

    static void pro() {
        // 작은 점부터 방문
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }
        visit = new boolean[N + 1];
        dfs(V);

        sb.append("\n");
        visit = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int x) {
        visit[x] = true;
        sb.append(x).append(' ');

        for (int y : adj[x]) {
            if (visit[y]) {
                continue;
            }
            dfs(y);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(' ');
            for (int next : adj[cur]) {
                if (visit[next]) {
                    continue;
                }
                q.offer(next);
                visit[next] = true;
            }
        }
    }
}
