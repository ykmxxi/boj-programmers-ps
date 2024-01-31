/*
    - n개 서로 다른 양의 정수 a1 ~ an, 각 수는 1 이상 백만 이하 (int)
    - x가 주어졌을 때 ai + aj = x를 만족하는 쌍의 수 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, X, ans = 0;
    static int[] A;

    static void input() {
        try {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            X = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void pro() {
        Arrays.sort(A);

        int L = 0;
        int R = N - 1;

        while (L < R) {
            if (A[L] + A[R] == X) {
                ans++;
            }
            if (A[L] + A[R] < X) {
                L++;
            } else {
                R--;
            }

        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
