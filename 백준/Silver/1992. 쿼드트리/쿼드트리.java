// https://www.acmicpc.net/problem/1992
// 시간 복잡도: O(N^2 logN) -> 최악의 경우 2^12 * log 64

import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0 ; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                arr[i][j] = ch[j] - '0';
            }
        }
    }

    static void pro() {
        // 4개 영역으로 쪼개서 탐색
        rec(N, 0, 0);
        System.out.print(sb);
    }

    static void rec(int len, int x, int y) {
        if (same(len, x, y)) {
            sb.append(arr[x][y]);
        } else {
            int mid = len / 2;
            sb.append("(");
            // 왼위, 오른위, 왼아래, 오른아래 차례대로
            rec(mid, x, y);
            rec(mid, x, y+mid);
            rec(mid, x+mid, y);
            rec(mid,x+mid,y+mid);
            sb.append(")");
        }
    }

    static boolean same(int len, int x, int y) {
        if (len == 1) {
            return true;
        }
        int cur = arr[x][y];
        // N^2
        // 첫 번쨰 행/열 비교 안함
        for (int nx = x; nx < x + len; nx++) {
            for (int ny = y; ny < y + len; ny++) {
                if (arr[nx][ny] != cur) {
                    return false;
                }
            }
        }
        return true;
    }
}
