import java.io.*;
import java.util.*;

// 시간 복잡도: O(N + M)
// 공간 복잡도: O(N + M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[] nums, dp;
    static int[][] ij;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i<= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        ij = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ij[i][0] = Integer.parseInt(st.nextToken());
            ij[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        // 완탐을 하면 최악의 경우 N * M 즉, 10^10 번의 연산 필요 -> 미리 구간 합을 구해놔 연산
        // i ~ j -> dp[i~j] = dp[j] - dp[i - 1]
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + nums[i]; // int 범위 최대의 경우 10^8 (1억)
        }

        for (int i = 0; i < M; i++) {
            int first = ij[i][0];
            int last = ij[i][1];

            sb.append(dp[last] - dp[first - 1]).append('\n');
        }

        System.out.print(sb);
    }
}
