/*
    - N * 2 행렬 동물원: 가로, 세로 붙어 있게 배치할 수 없음
    - 첫째 줄(1열)에 사자를 배치하는 경우의 수를 9901로 나눈 나머지 출력
    - 사자를 배치하지 않은 경우도 센다
    - 가짜문제: dy[i][0]: i행에 배치하지 않은 경우, dy[i][1]: i행 1열에 배치했을 때 경우의 수, dy[i][2]: i행 2열에 배치했을 때 경우의 수
    - 진짜문제: (dy[N][0] + dy[N][1] + dy[N][2]) % MOD
    - 점화식
        * dy[i][0] = dy[i - 1][0] + dy[i - 1][1] + dy[i - 1][2]
        * dy[i][1] = dy[i - 1][0] + dy[i - 1][2]
        * dy[i][2] = dy[i - 1][0] + dy[i - 1][1]
 */

import java.util.*;
import java.io.*;

public class Main {

    static final int MOD = 9901;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] dy;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        dy = new int[N + 1][3];
        dy[1][0] = 1;
        dy[1][1] = 1;
        dy[1][2] = 1;
        for (int i = 2; i <= N; i++) {
            dy[i][0] = ((dy[i - 1][0] + dy[i - 1][1]) % MOD + dy[i - 1][2]) % MOD;
            dy[i][1] = (dy[i - 1][0] + dy[i - 1][2]) % MOD;
            dy[i][2] = (dy[i - 1][0] + dy[i - 1][1]) % MOD;
        }

        System.out.println(((dy[N][0] + dy[N][1] % MOD) + dy[N][2]) % MOD);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
