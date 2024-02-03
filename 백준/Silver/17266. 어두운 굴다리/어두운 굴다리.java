/*
    - 0 ~ N을 가로등 M개 설치 -> 위치 x 들
    - 최소한의 높이로 굴다리 모든 길을 밝히려고 한다
    - 단, 가로등은 모두 높이가 같고 정수
    - 가로등 길이가 H -> 왼쪽으로 H, 오른쪽으로 H -> x - H ~ x + H
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N; // 굴다리 길이 1 ~ 100,000
    static int M; // 1 <= M <= N
    static int[] A; // 가로등 설치 위치

    static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            M = Integer.parseInt(br.readLine());

            A = new int[M];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean can(int H) {
        int before = 0;

        for (int x : A) {
            if (x - H > before) {
                return false;
            }
            before = x + H;
        }

        return before >= N;
    }

    static void pro() {
        int L = 1, R = N;
        int ans = N;

        while (L <= R) {
            int mid = (L + R) / 2;

            if (can(mid)) { // mid로 모두 비출 수 있으면
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
