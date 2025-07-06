// https://www.acmicpc.net/problem/2828
// 시간 복잡도: O(1)
// 바구니 이동 거리의 최솟값 -> 사과가 떨어지는 위치가 [L, R] 구간 안에 있는지 체킹, 다음 이동방향(왼쪽 or 오른쪽) 확인

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, J; // M < N
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        J = Integer.parseInt(br.readLine());
        A = new int[J];
        for(int i = 0; i < J; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        int L = 1; // 바구니 왼쪽 끝
        int R = L + M - 1; // 바구니 오른쪽 끝
        int ans = 0;
        int dir = 1;
        for (int i = 0; i < J; i++) {
            if (A[i] < L || A[i] > R) {
                int dist = Math.min(Math.abs(A[i] - L), Math.abs(A[i] - R));
                ans += dist;
                L += dist * dir;
                R += dist * dir;
            }
            if (i != J - 1) {
                if (A[i] <= A[i + 1]) {
                    dir = 1;
                } else {
                    dir = -1;
                }
            }
        }
        System.out.println(ans);
    }
}
