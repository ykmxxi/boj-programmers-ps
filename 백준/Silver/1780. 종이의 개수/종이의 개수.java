// https://www.acmicpc.net/problem/1780
// 시간 복잡도: O(N^2 logN)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[][] arr;
    static int a, b, c;

    public static void main(String[] args) throws IOException {
        input();

        pro();
        sb.append(a).append('\n').append(b).append('\n').append(c);
        System.out.println(sb);
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        // N x N -> -1, 0, 1
        // 종이가 모두 같은 수 -> 그대로 사용 / 모두 같지 않으면 같은 크기의 종이 9개로 자르고 다시 반복
        rec(N, 0, 0);
    }

    static void rec(int len, int x, int y) {
        if (same(len, x, y)) {
            if (arr[x][y] == -1) {
                a++;
            } else if (arr[x][y] == 0) {
                b++;
            } else {
                c++;
            }
        } else {
            // 9개로 쪼개야 함
            int nl = len / 3;
            for (int i = 0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    rec(nl, x+(nl*i), y+(nl*j));
                }
            }
        }
    }

    static boolean same(int len, int x, int y) {
        if (len == 1) {
            return true;
        }
        int cur = arr[x][y];
        for (int nx=x; nx<x+len; nx++) {
            for (int ny=y; ny<y+len; ny++) {
                if (cur != arr[nx][ny]) {
                    return false;
                }
            }
        }
        return true;
    }
}
