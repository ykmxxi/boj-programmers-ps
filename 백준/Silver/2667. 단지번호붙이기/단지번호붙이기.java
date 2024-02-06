import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, group_cnt;
    static String[] A;
    static boolean[][] visit;
    static ArrayList<Integer> group;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new String[N];

        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
        visit = new boolean[N][N];
    }

    static void dfs(int x, int y) {
        group_cnt++;
        visit[x][y] = true;

        // 인접한 집으로 새로운 방문
        for (int k = 0; k < 4; k++) {
            // (x, y) -> dir[k]
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (A[nx].charAt(ny) == '0') {
                continue;
            }
            if (visit[nx][ny]) {
                continue;
            }
            dfs(nx, ny);
        }

    }

    static void pro() {
        group = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && A[i].charAt(j) == '1') {
                    group_cnt = 0;
                    dfs(i, j);
                    group.add(group_cnt);
                }
            }
        }

        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for (int cnt : group) {
            sb.append(cnt).append('\n');
        }

        System.out.println(sb);

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
