/*
    - 줄어들지 않는다: 그 숫자의 각 자리 수보다 왼쪽 자리 수가 작거나 같을 때
    - 줄어들지 않는 4자리 숫자: 0011, 1111, 1112, 2223
    - 숫자의 앞에 0이 있어도 된다: 0000, 0001, 0002, 0003 올바른 숫자
    - 가짜문제: dy[i][0]: i자리 끝이 0으로 끝남, dy[i][9]: i자리 끝이 9로 끝남
    - 진짜문제: sum(dy[N][0] ~ dy[N][9])
    - 점화식: dy[i][0] = dy[i - 1][0], dy[i][1] = dy[i - 1][0] + dy[i - 1][1]
     -> dy[i][k] = sum(dy[i - 1][0] ~ dy[i - 1][k])
 */

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static long[][] dy;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pre() {
        dy = new long[65][10];

        for (int i = 0; i <= 9; i++) {
            dy[1][i] = 1L;
        }

        for (int i = 2; i <= 64; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dy[i][j] += dy[i - 1][k];
                }
            }
        }
    }

    static void pro() {
        long ans = 0L;
        for (long cnt : dy[N]) {
            ans += cnt;
        }
        sb.append(ans).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        pre();
        while(T-- > 0) {
            input();
            pro();
        }
        System.out.print(sb);
    }

}
