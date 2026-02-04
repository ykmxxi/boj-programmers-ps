import java.io.*;
import java.util.*;

// 시간 복잡도: O(N * M)
// 공간 복잡도: O(N * M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[][] adj; // 1 익토, 0 익X토, -1 빈칸
    static int[][] dist, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        adj = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            Arrays.fill(dist[i], -1);
            for (int j = 0; j < M; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                if (adj[i][j] == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[] {i, j});
                }
            }
        }
    }

    static void pro() {
       System.out.print(bfs());
    }

    static int bfs() {
        int ans = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (dist[nx][ny] != -1 || adj[nx][ny] == -1) {
                    continue;
                }
                q.offer(new int[]{nx, ny});
                adj[nx][ny] = 1;
                dist[nx][ny] = dist[x][y] + 1;
                ans = Math.max(ans, dist[nx][ny]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (adj[i][j] == 0) {
                    ans = -1;
                }
            }
        }
        return ans;
    }
}
