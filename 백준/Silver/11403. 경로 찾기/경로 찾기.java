/*
    - 가중치 없는 방향 그래프 G
    - 모든 정점 (i, j)에 대해서 i -> j
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Integer>[] adj;
    static boolean[] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            adj[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    adj[i].add(j);
                }
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        visit[start] = false; // 시작점으로 다시 돌아올 수 있는지 체크하기 위해 방문 상태 초기화

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int y : adj[x]) {
                if (visit[y]) {
                    continue;
                }
                visit[y] = true;
                q.add(y);
            }
        }
    }

    static void pro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            visit = new boolean[N];
            bfs(i);
            for (int j = 0; j < N; j++) {
                if (visit[j]) {
                    sb.append(1).append(' ');
                } else {
                    sb.append(0).append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
