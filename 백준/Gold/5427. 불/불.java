import java.io.*;
import java.util.*;

// 시간 복잡도: O(T * NM)
// 공간 복잡도: O(N*M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, N, M;
    static Queue<int[]> q1;
    static Queue<int[]> q2;
    static int[][] dist1, dist2, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[][] adj;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            input();
            pro();
        }
        System.out.print(sb);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        adj = new char[N][M];
        dist1 = new int[N][M];
        dist2 = new int[N][M];
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            Arrays.fill(dist1[i], -1);
            Arrays.fill(dist2[i], -1);
            for (int j = 0; j < M; j++) {
                adj[i][j] = line.charAt(j);
                if (adj[i][j] == '*') {
                    q1.offer(new int[]{i, j});
                    dist1[i][j] = 0;
                }
                if (adj[i][j] == '@') {
                    q2.offer(new int[]{i, j});
                    dist2[i][j] = 0;
                }
            }
        }
    }

    static void pro() {
        // 불의 이동경로 먼저 계산
        bfs1();

        // 상근이의 이동 경로 계산
        int ans = bfs2();
        if (ans == -1) {
            sb.append("IMPOSSIBLE").append('\n');
        } else {
            sb.append(ans).append('\n');
        }
    }

    static void bfs1() {
        while (!q1.isEmpty()) {
            int[] cur = q1.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (dist1[nx][ny] > -1 || adj[nx][ny] == '#') {
                    continue;
                }
                q1.offer(new int[]{nx, ny});
                dist1[nx][ny] = dist1[x][y] + 1;
            }
        }
    }

    static int bfs2() {
        while (!q2.isEmpty()) {
            int[] cur = q2.poll();
            int x = cur[0];
            int y = cur[1];
            if (x == 0 || y == 0 || x == N - 1 || y == M - 1) {
                return dist2[x][y] + 1;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (dist2[nx][ny] > -1 || adj[nx][ny] == '#') {
                    continue;
                }
                if (dist1[nx][ny] != -1 && dist1[nx][ny] <= dist2[x][y] + 1) {
                    continue;
                }
                q2.offer(new int[]{nx, ny});
                dist2[nx][ny] = dist2[x][y] + 1;
            }
        }
        return -1;
    }
}
