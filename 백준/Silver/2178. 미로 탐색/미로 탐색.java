import java.io.*;
import java.util.*;

// 시간 복잡도: O(N * M)
// 공간 복잡도: O(N * M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[][] adj, dist, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                adj[i][j] = line.charAt(j) - '0';
            }
        }
    }

    static void pro() {
        bfs(0, 0);
        System.out.print(dist[N - 1][M - 1]);
    }

    static void bfs(int sx, int sy) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(sx);
        q.offer(sy);
        dist[sx][sy] = 1;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (dist[nx][ny] != 0 || adj[nx][ny] == 0) { // 이미 방문했거나, 벽이면 넘어가
                    continue;
                }
                q.offer(nx);
                q.offer(ny);
                dist[nx][ny] = dist[x][y] + 1;
            }
        }
    }
}
