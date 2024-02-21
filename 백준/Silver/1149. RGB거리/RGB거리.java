/*
    - RGB 거리 집이 N개(1 ~ N)
    - 빨,초,파 중 하나 집을 칠함
    - 1, 2번 집의 색은 같지 않아야 함, N번 집의 색은 N - 1번 집의 색과 같지 않아야 함
    - i번(2 <= i <= N - 1) 집의 색은 i - 1번 i + 1번 색과 같지 않아야 함
    - 가짜문제: dy[i[[0] i번째 집을 빨강으로 칠했을 때 비용
    - 진짜문제: min(dy[N][0], dy[N][1], dy[N][2])
    - 점화식
        * dy[i][0] = A[i][0] + min(dy[i - 1][1], dy[i - 1][2])
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] A, dy;
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        A = new int[N + 1][3]; // i번 집 빨, 초, 파
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        dy = new int[N + 1][3];

        dy[1][0] = A[1][0];
        dy[1][1] = A[1][1];
        dy[1][2] = A[1][2];
        for (int i = 2; i <= N; i++) {
            dy[i][0] = Math.min(dy[i - 1][1], dy[i - 1][2]) + A[i][0];
            dy[i][1] = Math.min(dy[i - 1][0], dy[i - 1][2]) + A[i][1];
            dy[i][2] = Math.min(dy[i - 1][0], dy[i - 1][1]) + A[i][2];
        }

        System.out.println(Math.min(dy[N][0], Math.min(dy[N][1], dy[N][2])));
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
