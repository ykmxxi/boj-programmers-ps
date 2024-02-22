/*
    - 정사각형 모눈종이 -> 행과 열
    - 양의 정수의 소용돌이 모양으로 채운다 -> 1을 0행 0열에, 0행 1열에 숫자 2
    - 소용돌이를 반시계 방향으로 채우기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int r1, c1, r2, c2;
    static int[][] A, dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
    }

    static boolean check() {
        return A[0][0] != 0 && A[r2 - r1][0] != 0 && A[0][c2 - c1] != 0 && A[r2 - r1][c2 - c1] != 0;
    }
    static void pro() {
        A = new int[r2 - r1 + 1][c2 - c1 + 1];

        int x = 0, y = 0, d = 0, max;
        int num = 1, len = 1, cnt = 0;
        while (!check()) {
            if (x >= r1 &&  y >= c1 && x <= r2 && y <= c2) {
                A[x - r1][y - c1] = num;
            }

            num++;
            cnt++;
            x += dir[d][0];
            y += dir[d][1];

            if (cnt == len) {
                cnt = 0;
                if (d == 1 || d == 3) { // 방향이 위 or 아래
                    len++;
                }
                d = (d + 1) % 4;
            }
        }
        max = num - 1;

        print(max);
    }

    static void print(int max) {
        StringBuilder sb = new StringBuilder();

        int maxLen = String.valueOf(max).length();
        int len = 0;

        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                len = maxLen - String.valueOf(A[i][j]).length();
                for (int k = 0; k < len; k++) {
                    sb.append(' ');
                }
                sb.append(A[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
