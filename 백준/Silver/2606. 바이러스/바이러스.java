/*
    - 한 컴퓨터가 바이러스에 걸리면 네트워크 상 연결된 모든 컴퓨터가 바이러스에 걸림
    - 1번 컴퓨터가 바이러스에 걸렸을 때 바이러스에 걸리게 되는 컴퓨터의 수를 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static List<Integer>[] adj;
    static boolean[] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }

        visit = new boolean[N + 1];
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

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
        // 1번 컴퓨터 시작
        bfs(1);

        int ans = -1;
        for (int i = 1; i <= N; i++) {
            if (visit[i]) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
