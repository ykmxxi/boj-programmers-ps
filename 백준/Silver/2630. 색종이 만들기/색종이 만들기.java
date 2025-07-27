// https://www.acmicpc.net/problem/2630
// 시간 복잡도: O(N^2 logN)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    static int[][] arr;
    static int N, a, b;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i <N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        rec(N, 0, 0);
        sb.append(a).append('\n').append(b);
        System.out.print(sb);
    }

    static void rec(int len, int x, int y) {
        if (same(len, x, y)) {
            if (arr[x][y] == 0) {
                a++;
            } else {
                b++;
            }
        } else {
            int nl = len/2;
            for (int i = 0; i < 2; i++) {
                for (int j=0; j<2; j++) {
                    rec(nl,x+(i*nl), y+(j*nl));
                }
            }
        }
    }

    static boolean same(int len, int x, int y) {
        if (len == 1) {
            return true;
        }
        int cur = arr[x][y];
        for (int nx = x; nx<x+len; nx++) {
            for (int ny=y; ny<y+len; ny++) {
                if (arr[nx][ny] != cur) {
                    return false;
                }
            }
        }
        return true;
    }
}
