/*
    - 케빈 케이컨의 수가 가장 작은 사람 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M; // N: 정점의 개수, M: 간선의 개수
    static List<Integer>[] adj;
    static int[] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static int bfs(int x) {
        Queue<Integer> q = new LinkedList<>();
        int res = 0;
        dist[x] = 0;
        q.add(x);

        while (!q.isEmpty()) {
            x = q.poll();

            for (int y : adj[x]) {
                if (dist[y] != -1) {
                    continue;
                }
                dist[y] = dist[x] + 1;
                res += dist[y];
                q.add(y);
            }
        }
        return res;
    }

    static void pro() {
        int ans = 0;
        int min = Integer.MAX_VALUE;
        dist = new int[N + 1];

        // 1 ~ N 까지 시작점으로 설정해 거리 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[j] = -1;
            }

            int res = bfs(i);
            if (res < min) {
                min = res;
                ans = i;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
