/*
    - 0과 1로 이루어진 이진수 중 특별한 성질을 갖는 이친수
        * 0으로 시작하지 않는다
        * 1이 두 번 연속으로 나타나지 않는다. (11)을 부분 문자열로 갖지 않는다
    - 가짜문제: dy[i][0]: i길이, 0으로 끝나는 경우, dy[i][1]: 1길이, 1로 끝나는 경우
    - 진짜문제: dy[N][0] + dy[N][1]
    - 점화식: dy[i][0] = dy[i - 1][0] + dy[i - 1][1], dy[i][1] = dy[i - 1][0]
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[][] dy;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 1 <= N <= 90
    }

    static void pro() {
        dy = new long[N + 1][2]; // dy[i][0]: i 길이, 끝이 0인 경우, dy[i][1]: i 길이, 끝이 1인 경우

        dy[1][0] = 0L;
        dy[1][1] = 1L;

        for (int i = 2; i <= N; i++) {
            dy[i][0] = dy[i - 1][0] + dy[i - 1][1];
            dy[i][1] = dy[i - 1][0];
        }

        System.out.println(dy[N][0] + dy[N][1]);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
