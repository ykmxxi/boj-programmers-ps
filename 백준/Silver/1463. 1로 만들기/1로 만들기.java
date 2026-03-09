import java.io.*;
import java.util.*;

// 시간 복잡도: O(1_000_000)
// 공간 복잡도: O(1_000_000)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        dp = new int[1_000_001];
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // X가 3으로 나누어 떨어지면 3으로 나눈다
        // X가 2로 나누어 떨어지면 2로 나눈다
        // 1을 뺀다
        // N이 주어졌을 때 연산 세 개를 적절히 사용해서 1을 만들기 -> 연산을 사용하는 횟수의 최소값
        pre();

        System.out.print(dp[N]);
    }

    static void pre() {
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= 1_000_000; i++) {
            if (i % 3 == 0 && i % 2 == 0) {
                dp[i] = Math.min(dp[i - 1] + 1, Math.min(dp[i / 3] + 1, dp[i / 2] + 1));
                continue;
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i - 1] + 1, dp[i / 3] + 1);
                continue;
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i - 1] + 1, dp[i / 2] + 1);
                continue;
            }
            dp[i] = dp[i - 1] + 1;
        }
    }
}
