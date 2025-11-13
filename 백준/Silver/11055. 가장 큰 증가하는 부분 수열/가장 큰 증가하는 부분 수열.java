
import java.io.*;
import java.util.*;

// 시간 복잡도: O(N^2)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N; // 10^3 -> O(N^2) 10^6 이내
    static int[] A, dp;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        // 수열의 증가하는 부분 수열 중 합이 가장 큰 것
        dp = new int[N];
        System.arraycopy(A, 0, dp, 0, N);
        int ans = dp[0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
