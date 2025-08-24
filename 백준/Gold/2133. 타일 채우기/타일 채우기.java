// 타일 채우기: https://www.acmicpc.net/problem/2133
// 시간 복잡도: O(N^2) -> 두 번째 반복문이 N - 4 로 시작해 -=2 N^2 보다 작음, N 최대값이 작아 영향 X 

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] dp = new int[31];

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // 3xN 크기의 벽을 2x1, 1x2 크기의 타일로 채우는 경우의 수
        // 홀수이냐 짝수이냐 달라짐
        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }
        dp[0] = 1; // 점화식을 채우기 위한 초기화
        dp[2] = 3; // 3x2: 1x2 3개, 2x1위/아래 1x2 2개 -> 3개의 경우의 수

        // dp[4]를 생각하면 dp[2], 즉 3x2의 3가지 경우의 수 3x3 -> 9개
        for (int i = 4; i <= N; i += 2) { // 홀수는 불가
            dp[i] = dp[i - 2] * 3; // 2칸을 채우는 3가지 경우의수 존재
            for (int j = i - 4; j >= 0; j -= 2) { // 4칸을 채우는 2개의 경우의 수 존재
                dp[i] += dp[j] * 2; // 4칸을 예로 들면 앞이 4칸 뒤가 없음, 앞이 없고 뒤가 4칸 -> 2개 
            }
        }

        System.out.println(dp[N]);
    }
}
