/*
    - N x M 격자형 배열, 벽 or 빈 칸
    - 청소기 이동(상하좌우)
    - 현재 칸이 청소되지 않은 경우 청소
        1. 주변 4칸 중 청소되지 않은 빈칸이 없는 경우: 방향 유지, 한 칸 후진, 처음으로 돌아가기 or 벽인 경우 멈춘다
        2. 주변 4칸 중 청소되지 않은 빈칸이 있는 경우: 반시계 방향으로 90도 회전, 방향의 앞 칸이 청소 X이면 전진 후 처음으로
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, r, c, d, ans;
    static int[][] A;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int x, int y, int curD) {
        A[x][y] = 2;

        for (int k = 0; k < 4; k++) {
            curD = (curD + 3) % 4;

            int nx = x + dir[curD][0];
            int ny = y + dir[curD][1];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (A[nx][ny] == 0) {
                    ans++;
                    dfs(nx, ny, curD);
                    return;
                }
            }
        }

        int back = (curD + 2) % 4;
        int bx = x + dir[back][0];
        int by = y + dir[back][1];

        if (bx >= 0 && by >= 0 && bx < N && by < M) {
            if (A[bx][by] != 1) {
                dfs(bx, by, curD);
            }
        }

    }

    static void pro() {
        ans = 1;

        dfs(r, c, d);

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
