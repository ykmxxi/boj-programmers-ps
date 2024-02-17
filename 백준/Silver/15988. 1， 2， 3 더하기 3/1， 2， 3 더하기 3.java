/*
    - 가짜문제: dy[i] -> i를 1, 2, 3의 합으로 나타내는 방법의 수
    - 진짜문제: dy[N] -> N을 1, 2, 3의 합으로 나타내는 방법의 수
    - 점화식: dy[i] = (dy[i - 1] + dy[i - 2] + dy[i - 3) % 1,000,000,009

 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int MOD = 1_000_000_009;
    static int N;
    static int[] dy;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pre() {
        dy = new int[1_000_001];

        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 4;

        for (int i = 4; i <= 1_000_000; i++) {
            dy[i] += dy[i - 1];
            dy[i] += dy[i - 2];
            dy[i] %= MOD;
            dy[i] += dy[i - 3];
            dy[i] %= MOD;
        }
    }

    static void pro() {
        sb.append(dy[N]).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        pre();
        while (T-- > 0) {
            input();
            pro();
        }
        System.out.print(sb);
    }
}
