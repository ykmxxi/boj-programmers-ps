import java.io.*;
import java.util.*;

// 시간 복잡도: O(1)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[] dp;
    static int MOD = 10_007;

    public static void main(String[] args) throws IOException {
            input();
            pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // 마지막에 1x2 타일 2개로 채울 것인가? 아니면 2x1로 채울것인가
        pre();

        System.out.print(dp[N]);
    }

    static void pre() {
        dp = new int[1005]; // 2x1000 까지 미리 채워둔다
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= 1000; i++) {
            dp[i] = ((dp[i - 1] % MOD) + (dp[i - 2] % MOD)) % MOD; // 마자막을 1x2로 채운다 + 마지막을 2x2로 채운다
        }
    }
}
