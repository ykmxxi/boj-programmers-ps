import java.io.*;
import java.util.*;

// 시간 복잡도:
// 공간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] adj, visit;
    static List<int[]> cctv;

    // 1번 1개씩, 2번: 13 or 24, 3번: 14 or 21 or 32 or 43
    // 4번: 134 or 241 or 312 or 423, 5번: 1234
    static int[][][] dirs = {
        {},
        {{0}, {1}, {2}, {3}},
        {{0, 2}, {1, 3}},
        {{0,3}, {1, 0}, {2, 1}, {3, 2}},
        {{0,2,3}, {1,3,0}, {2,0,1}, {3,1,2}},
        {{0,1,2,3}}
    };

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new int[N][M];
        visit = new int[N][M];
        cctv = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                if (adj[i][j] >= 1 && adj[i][j] <= 5) {
                    cctv.add(new int[]{i, j});
                }
            }
        }
    }

    static void pro() {
        // 1번 1개씩, 2번: 13 or 24, 3번: 14 or 21 or 32 or 43
        // 4번: 134 or 241 or 312 or 423, 5번: 1234
        dfs(0);

        System.out.print(ans);
    }

    static void dfs(int depth) {
        if (depth == cctv.size()) { // cctv의 모든 방향을 결정
            ans = Math.min(ans, calculate());
            return;
        }

        int[] cur = cctv.get(depth);
        int x = cur[0];
        int y = cur[1];
        int t = adj[x][y];

        for (int[] d : dirs[t]) {
            for (int k : d) {
                update(x, y, k, 1);
            }

            dfs(depth + 1);

            // 복구
            for (int k : d) {
                update(x,y,k, -1);
            }
        }
    }

    static void update(int x, int y, int k, int val) {
        while (true) {
            x += dir[k][0];
            y += dir[k][1];

            if (x < 0 || y < 0 || x >= N || y >= M) {
                break;
            }
            if (adj[x][y] == 6) {
                break;
            }
            if (adj[x][y] == 0) {
                visit[x][y] += val;
            }
        }
    }

    static int calculate() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (adj[i][j] == 0 && visit[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
