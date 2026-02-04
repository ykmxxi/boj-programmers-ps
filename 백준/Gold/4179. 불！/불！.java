import java.io.*;
import java.util.*;

// 시간 복잡도: O(r * c)
// 공간 복잡도: O(r * c)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int r, c, jx, jy, fx, fy; // 1 <= r, c <= 1,000
    static int[][] dir = {{-1,0}, {1,0},{0,-1},{0,1}};
    static char[][] adj;
    static int[][] distF, distJ;
    static int ans = -1;
    static Queue<int[]> fq = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        adj = new char[r][c];
        distJ = new int[r][c];
        distF = new int[r][c];
        for (int i = 0; i< r; i++) {
            String line = br.readLine();
            Arrays.fill(distJ[i], -1);
            Arrays.fill(distF[i], -1);
            for (int j = 0; j < c; j++) {
                adj[i][j] = line.charAt(j);
                if (adj[i][j] == 'J') {
                    jx = i;
                    jy = j;
                }
                if (adj[i][j] == 'F') {
                    fq.offer(new int[]{i, j});
                    distF[i][j] = 0;
                }
            }
        }
    }

    static void pro() {
        // 위치와 불이 붙은 위치를 감안해 탈출 여부, 얼마나 빨리 탈출할 수 있는지
        // #(벽), .(공간), J(지훈), F(불이 난 곳)
        // bfs 2번: 불의 확산 속도 먼저 계산, 이후 지훈의 bfs 돌면서 불의 확산 속도와 비교하면서 갈 수 있는지 판단

        bfs1(fx, fy); // 불의 확산 속도 계산

        bfs2(jx, jy); // 지훈의

        if (ans == -1) {
            System.out.print("IMPOSSIBLE");
        } else {
            System.out.print(ans);
        }
    }

    static void bfs1(int sx, int sy) {
        while (!fq.isEmpty()) {
            int[] cur = fq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    continue;
                }
                if (distF[nx][ny] != -1 || adj[nx][ny] == '#') {
                    continue;
                }
                fq.offer(new int[]{nx, ny});
                distF[nx][ny] = distF[x][y] + 1;
            }
        }
    }

    static void bfs2(int sx, int sy) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        distJ[sx][sy] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == 0 || y == 0 || x == r-1 || y == c-1) {
                ans = distJ[x][y] + 1;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    continue;
                }
                if (distJ[nx][ny] != -1 || adj[nx][ny] == '#') {
                    continue;
                }
                if (distF[nx][ny] != -1 && distF[nx][ny] <= distJ[x][y] + 1) {
                    continue;
                }
                q.offer(new int[]{nx, ny});
                distJ[nx][ny] = distJ[x][y] + 1;
            }
        }
    }
}
