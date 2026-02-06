import java.io.*;
import java.util.*;

// 시간 복잡도: O(N)
// 공간 복잡도: O(N^2)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, maxH = 0, ans = 0;
    static int[][] adj, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                if (adj[i][j] > maxH) {
                    maxH = adj[i][j];
                }
            }
        }
    }

    static void pro() {
        // 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개
        for (int h = 0; h < maxH; h++) {
            int cnt = 0;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j] || adj[i][j] <= h) {
                        continue;
                    }
                    dfs(i, j, h);
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }

        System.out.print(ans);
    }

    static void dfs(int x, int y, int max) {
        visit[x][y] = true;

        for(int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (nx < 0 || ny < 0 || nx >=N || ny>=N) {
                continue;
            }
            if (visit[nx][ny] || adj[nx][ny] <= max) {
                continue;
            }
            dfs(nx, ny, max);
        }
    }
}
