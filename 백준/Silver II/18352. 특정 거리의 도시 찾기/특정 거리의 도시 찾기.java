import java.util.*;
import java.io.*;

public class Main {

    static int N, M, X, K;
    static int[] dist;
    static List<Integer>[] adj;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = -1;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int y : adj[x]) {
                if (dist[y] != -1) {
                    continue;
                }
                dist[y] = dist[x] + 1;
                q.add(y);
            }
        }
    }

    static void pro() {
        bfs(X);

        boolean exist = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                exist = true;
                sb.append(i).append('\n');
            }
        }

        if (exist) {
            System.out.print(sb);
        } else {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
