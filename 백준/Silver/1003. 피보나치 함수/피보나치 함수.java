/*
    - 가짜문제: dy[i][0] -> f(i) 호출 시 0의 출력 회수, dy[i][1] -> f(i) 호출 시 1의 출력 회수
    - 진짜문제: dy[N][0], dy[N][1]
    - 점화식
      dy[i][0] -> dy[i - 1][0] + dy[i - 2][0]
      dy[i][1] -> dy[i - 1][1] + dy[i - 2][1]

 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] dy;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pre() {
        dy = new int[41][2];

        dy[0][0] = 1;
        dy[0][1] = 0;
        dy[1][0] = 0;
        dy[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dy[i][0] = dy[i - 1][0] + dy[i - 2][0];
            dy[i][1] = dy[i - 1][1] + dy[i - 2][1];
        }
    }

    static void pro() {
        sb.append(dy[N][0]).append(' ').append(dy[N][1]).append('\n');
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
