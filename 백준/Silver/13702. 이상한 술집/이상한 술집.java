/*
    - 주전자 용량 똑같음, 용량은 랜덤
    - N 주전자 주문, K명에게 똑같은 양으로 나눈다
    - 다른 주전자의 막걸리가 섞이는 것 X -> 나누고 남은것은 버린다
    - K명에게 최대한의 많은 양의 막걸리를 배분할 수 있는 용량 구하기(최대값) -> 이분 탐색 or 매개변수 탐색
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[] A;
    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken()); // N개 막걸리 주전자
            K = Integer.parseInt(st.nextToken()); // K명

            A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean can(long amount) {
        int cnt = 0;
        for (int a : A) {
            cnt += a / amount;
        }
        return cnt >= K;
    }

    static void pro() {
        long L = 0L; // 0일 수도
        long R = Integer.MAX_VALUE;
        long ans = 0L;

        while (L <= R) {
            long mid = (L + R) / 2;

            if (can(mid)) { // mid 용량으로 K명에게 나눠줄 수 있으면
                ans = mid;
                L = mid + 1; // 최대값이니까 L을 최대한 늘리기
            } else {
                R = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
