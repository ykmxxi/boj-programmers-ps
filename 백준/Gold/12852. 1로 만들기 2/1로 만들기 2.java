import java.io.*;
import java.util.*;

// 시간 복잡도: O(N) + O(log N) -> O(N) (N <= 1,000,000)
// 공간 복잡도: O(N) (N <= 1,000,000)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // X 연산 -> 3으로 나누어 떨어지면 3으로 나눈다 / 2로 나누어 떨어지면 2로 나눈다 / 1로 뺀다
        // N이 주어졌을 때 연산 세 개를 사용해서 1을 만든다. 최소 연산 횟수, 1로 만들어지는 과정
        // dp + 백트래킹 -> 마지막 연산을 기록
        dp = new int[1_000_005][3]; // dp[i][0]: 마지막에 3으로 나누어 기록 / [i][1] 2로 나누어 / [i][2] 1을 뺀 경우
        for (int i = 1; i <= 1_000_000; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[1][0] = 0;
        dp[1][1] = 0;
        dp[1][2] = 0;

        dp[2][1] = 1;
        dp[2][2] = 1;

        dp[3][0] = 1;
        dp[3][2] = 2;

        for (int i = 4; i <= 1_000_000; i++) {
            if (i % 3 == 0) {
                dp[i][0] = Math.min(dp[i / 3][0], Math.min(dp[i / 3][1], dp[i / 3][2])) + 1;
            }
            if (i % 2 == 0) {
                dp[i][1] = Math.min(dp[i / 2][0], Math.min(dp[i / 2][1], dp[i / 2][2])) + 1;
            }
            dp[i][2] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
        }

        int oper = 0;
        int cnt = dp[N][0];
        for (int i = 1; i < 3; i++) {
            if (dp[N][i] < cnt) {
                cnt = dp[N][i];
                oper = i;
            }
        }

        sb.append(cnt).append('\n');

        while (true) {
            sb.append(N).append(' ');
            if (N == 1) {
                break;
            }

            if (oper == 0) {
                N /= 3;
            } else if (oper == 1) {
                N /= 2;
            } else {
                N -= 1;
            }

            // 다음 연산 구하기
            oper = 0;
            cnt = dp[N][0];
            for (int i = 1; i < 3; i++) {
                if (dp[N][i] < cnt) {
                    oper = i;
                    cnt = dp[N][i];
                }
            }
        }

        System.out.print(sb);
    }
}
