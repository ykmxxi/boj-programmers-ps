import java.io.*;
import java.util.*;

// 시간 복잡도: O(L * R * C)
// 공간 복잡도: O(L * R * C), 1 <= L, R, C <= 30
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int L = -1, R = -1, C = -1, sx, sy, sz;
    static char[][][] adj;
    static int[][][] dist;
    static int[][] dir = {{-1,0,0}, {1, 0,0}, {0,-1,0}, {0,1,0}, {0,0,-1},{0,0,1}};

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            input();
            pro();
        }

        System.out.print(sb);
    }

    static void input() throws IOException {
        adj = new char[L][R][C];
        dist = new int[L][R][C];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                String line = br.readLine();
                for (int k = 0; k < C; k++) {
                    adj[i][j][k] = line.charAt(k);
                    if (adj[i][j][k] == 'S') {
                        sz = i;
                        sx = j;
                        sy = k;
                    }
                }
                Arrays.fill(dist[i][j], -1);
            }
            br.readLine();
        }
    }

    static void pro() {
        // 정육면체
        int ans = bfs();

        if (ans == -1) {
            sb.append("Trapped!").append('\n');
        } else {
            sb.append("Escaped in " + ans + " minute(s).").append('\n');
        }
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sz,sx,sy});
        dist[sz][sx][sy] = 0;
        int ans = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int z = cur[0];
            int x = cur[1];
            int y = cur[2];
            if (adj[z][x][y] == 'E') {
                ans = dist[z][x][y];
                break;
            }

            for (int k = 0; k < 6; k++) {
                int nz = z + dir[k][0];
                int nx = x + dir[k][1];
                int ny = y + dir[k][2];

                if (nz < 0 || nx < 0 || ny < 0 || nz >= L || nx >= R || ny >= C) {
                    continue;
                }
                if (dist[nz][nx][ny] != -1 || adj[nz][nx][ny] == '#') {
                    continue;
                }
                q.offer(new int[]{nz, nx, ny});
                dist[nz][nx][ny] = dist[z][x][y] + 1;
            }
        }

        return ans;
    }
}
