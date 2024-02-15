/*
    - 동기 N명(1 ~ N, 상근이는 1)
    - 결혼식에 초대할 수 있는 사람의 수 구하기: 자신의 친구 + 친구의 친구, dist[i] <= 2 초대 가능
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] dist;
    static List<Integer>[] adj;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = -1;
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        dist[start] = 0;
        q.add(start);

        while (!q.isEmpty()) {
            int x = q.poll();

            if (dist[x] >= 2) {
                break;
            }
            for (int y : adj[x]) {
                if (dist[y] != -1) {
                    continue;
                }
                cnt++;
                dist[y] = dist[x] + 1;
                q.add(y);
            }
        }

        return cnt;
    }

    static void pro() {
        System.out.println(bfs(1));
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
