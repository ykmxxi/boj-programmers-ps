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

    static int N, M, x, y, d, empty = 0;
    static int[][] A;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] == 0) {
                    empty++;
                }
            }
        }
    }

    static boolean stop() {
        //  바라보는 방향의 뒤쪽 칸이 벽이면 후진
        int nx = x + dir[(d + 2) % 4][0];
        int ny = y + dir[(d + 2) % 4][1];
        if (nx >= 0 && ny >= 0 && nx < N && ny < M && A[nx][ny] == 1) {
            return true;
        }
        return false;
    }

    static boolean check() {
        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && A[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }

    static void rotate() {
        if (d == 0) {
            d = 3;
        } else {
            d--;
        }
    }

    static void pro() {
        int ans = 0;
        while (true) {
            if (empty == 0) {
                break;
            }
            if (A[x][y] == 0) { // 현재 칸이 청소되지 않은 빈칸이면 청소
                A[x][y] = 2; // 청소된 빈 칸으로 변경
                empty--;
                ans++;
            }

            if (check()) { // 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                // 90 회전
                do {
                    rotate();
                } while (A[x + dir[d][0]][y + dir[d][1]] != 0);
                x += dir[d][0];
                y += dir[d][1];
            } else { // 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                if (stop()) {
                    break;
                }
                // 1칸 후진
                x += dir[(d + 2) % 4][0];
                y += dir[(d + 2) % 4][1];
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
