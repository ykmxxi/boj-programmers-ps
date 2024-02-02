/*
    - 길이 N 수열, 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N; // 1 <= N <= 100,000
    static int[] A; // 1 <= A[i] <= 100,000
    static int[] exist;

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            A = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void pro() {
        long ans = 0L;
        int R = -1;
        exist = new int[100001];

        for (int L = 0; L < N; L++) {
            while (R + 1 < N && exist[A[R + 1]] == 0) {
                R++;
                exist[A[R]] = 1;
            }

            ans += (R - L + 1);

            exist[A[L]] = 0;
        }


        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
