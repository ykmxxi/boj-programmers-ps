/*
    - 스티커 2n개를 구매 -> 2행 n열
    - 스티커를 떼면 변을 공유하는 스티커는 모두 찢어져 사용 불가(상하좌우 사용 불가)
    - 각 스티커에 점수를 메기고, 점수의 합이 최대
    - 가짜문제: dy[1][i]: 1행 i열 스티커 뗐을 때 점수, dy[2][i]: 2행 i열 스티커 뗏을 때 점수
    - 진짜문제: max(dy[1][N], dy[2][N])
    - 점화식
     * dy[1][i] = max(dy[2][i - 1], dy[1][i - 2], dy[2][i - 2]) + A[1][i]
     * dy[2][i] = max(dy[1][i - 1], dy[1][i - 2], dy[2][i - 2]) + A[2][i]
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] A, dy;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        A = new int[3][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= 2; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        dy = new int[3][N + 1];

        dy[1][1] = A[1][1];
        dy[2][1] = A[2][1];

        for (int i = 2; i <= N; i++) {
            dy[1][i] = Math.max(dy[2][i - 1], Math.max(dy[1][i - 2], dy[2][i - 2])) + A[1][i];
            dy[2][i] = Math.max(dy[1][i - 1], Math.max(dy[1][i - 2], dy[2][i - 2])) + A[2][i];
        }

        sb.append(Math.max(dy[1][N], dy[2][N])).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            pro();
        }
        System.out.print(sb);
    }
}
