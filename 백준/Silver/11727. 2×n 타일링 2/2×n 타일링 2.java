// 2xn 타일링 2: https://www.acmicpc.net/problem/11727
// 시간 복잡도: O(N)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N; // 1 <= N <= 1,000
    static int[] ans;
    static int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = new int[1001];
    }

    static void pro() {
        // 2xn 직사각형을 1x2, 2x1, 2x2 타일로 채우는 방법의 수 구하기
        // n을 몇 가지 경우의수로 채우는지 차례차례 계산 2x1 -> 2x2 -> 2x3 ... -> 2xn
        ans[1] = 1;
        ans[2] = 3;

        for (int i = 3; i <= 1000; i++) {
            // 2x(i-1)에 2x1 타일을 넣는 방법, 2x(i-2)에 2x2 타일을 넣는 방법, 2x(i-2)에 1x2 타일 2개를 넣는 방법
            ans[i] += (ans[i - 1] + ans[i - 2] + ans[i - 2]) % MOD;
        }

        System.out.println(ans[N]);
    }
}
