// 동전 바꿔주기: https://www.acmicpc.net/problem/2624
// 시간 복잡도: O(K*T)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, K;
    static int[][] coin;
    static int[][] dp; // k개 종류의 동전으로 만들 수 있는 금액을 기록 -> 누적합

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        coin = new int[K + 1][2];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            coin[i][0] = Integer.parseInt(st.nextToken());
            coin[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        // k 종류의 동전이 n1, n2, ..., nk개 존재
        // T원 -> 동전, 지폐를 동전으로 교환하는 방법의 가지 수 계산
        dp = new int[K + 1][T + 1];
        dp[0][0] = 1; // 초기화
        for (int i = 1; i <= K; i++) {
            int won = coin[i][0];
            int count = coin[i][1];

            for (int j = 0; j <= count; j++) { // 동전 n개를 기반으로 만들 수 있는 모든 돈을 기록

                for (int beforeWon = 0; beforeWon <= T; beforeWon++) {
                    int curWon = beforeWon + won * j; // 현재 새로운 금액
                    if (curWon > T) {
                        break;
                    }
                    dp[i][curWon] += dp[i - 1][beforeWon]; // i - 1개의 동저으로 beforeWon 금을 만드는 방법 + j개를 붙여 curWon 원을 만든다
                }
            }
        }

        System.out.println(dp[K][T]);
    }
}
