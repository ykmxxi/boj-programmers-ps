/*
    - 상하좌우 인접한 곳의 바다(0)의 수 만큼 빙하가 녹음
    - 두 덩어리 이상의 빙하로 분리되는 최초의 시간
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] A, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void melt(int x, int y) {
        int cnt = 0;
        visit[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (A[nx][ny] == 0 && !visit[nx][ny]) {
                cnt++;
            }
        }

        if (A[x][y] >= cnt) {
            A[x][y] -= cnt;
        } else {
            A[x][y] = 0;
        }
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (A[nx][ny] == 0 || visit[nx][ny]) {
                continue;
            }
            dfs(nx, ny);
        }
    }

    static void pro() {
        int ans = 0;
        int year = 0;

        // 1년 동안 녹이기
        while (true) {
            year++;
            visit = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (A[i][j] == 0) {
                        continue;
                    }
                    melt(i, j);
                }
            }

            // 두 덩어리 이상으로 분리 되었는지 확인
            int cnt = 0;
            visit = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (A[i][j] == 0 || visit[i][j]) {
                        continue;
                    }
                    dfs(i, j);
                    cnt++;
                }
                if (cnt >= 2) {
                    break;
                }
            }

            if (cnt == 0) {
                break;
            }
            if (cnt >= 2) {
                ans = year;
                break;
            }
        }

        System.out.println(ans);

    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
