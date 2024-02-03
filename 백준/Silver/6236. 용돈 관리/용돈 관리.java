/*
    - N일 동안 사용할 금액을 계산, M번만 통장에서 인출
    - K원 인출하며, 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용, 모자라면 남은 금액은 통장에 넣고 다시 K원 인출
    - M 숫자를 좋아해 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많아도 남은 금액은 통장에 집어넣고 다시 K원 인출
    - K를 최소화(최소값) -> 이분 탐색 or 매개변수 탐색
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M; // 1 <= N <= 100,000, 1 <= M <= N
    static int[] A;

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean determination(int K) {
        int cnt = 1;
        int remain = K;
        for (int a : A) {
            if (a > K) { // 인출하려는 금액으로 부족하면 실패
                return false;
            }
            if (remain >= a) {
                remain -= a;
            } else {
                remain = K;
                remain -= a;
                cnt++;
            }
        }
        return cnt <= M;
    }

    static void pro() {
        int L = 1; // 최소 금액
        int R = 1000000000; // 최대 금액 -> M = 1일 때, 모두 100,000일 동안 10,000원 사용하면 -> 10^9
        int ans = 0;

        while (L <= R) {
            int mid = (L + R) / 2;

            if (determination(mid)) { // mid 금액으로 가능하면
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
