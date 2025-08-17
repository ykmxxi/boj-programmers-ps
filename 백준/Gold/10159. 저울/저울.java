// 저울: https://www.acmicpc.net/problem/10159
// 시간 복잡도: O(N *(N + M)) -> 정점마다 bfs 2번

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static List<Integer>[] adj1, adj2;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine()); // 정점의 개수
        M = Integer.parseInt(br.readLine()); // 간선의 개수

        adj1 = new ArrayList[N + 1];
        adj2 = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj1[i] = new ArrayList<>(); // 가벼운것과 이어지는 단방향 그래프
            adj2[i] = new ArrayList<>(); // 무것운것과 이어지는 단방향 그래프
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj1[x].add(y);
            adj2[y].add(x);
        }
    }

    static void pro() {
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            int a = bfs(adj1, i);
            int b = bfs(adj2, i);
            sb.append(N - 1 - a - b).append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(List<Integer>[] adj, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start] = true;
        int dist = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : adj[x]) {
                if (visit[y]) {
                    continue;
                }
                visit[y] = true;
                dist++;
                q.offer(y);
            }
        }

        return dist;
    }
}
