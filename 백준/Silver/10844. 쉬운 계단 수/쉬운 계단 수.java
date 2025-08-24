// 쉬운 계단 수: https://www.acmicpc.net/problem/10844
// 시간 복잡도: O(N)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N; // 1 <= N <= 100;
    static long MOD = 1_000_000_000;
    static long[][] dp = new long[101][10]; // 자리수, 수 -> dp[2][1] 길이 2의 1로 끝나는 계단 수

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // 계단 수: 45656 처럼 인접한 모든 자리의 수가 1 (부호 상관 없이 1 차이)
        // 길이가 N인 계단 수의 총 개수 -> 0으로 시작하는 수는 계단 수가 아님
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1L;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] % MOD;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % MOD;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += dp[N][i];
        }
        System.out.println(ans % MOD);
    }
}
