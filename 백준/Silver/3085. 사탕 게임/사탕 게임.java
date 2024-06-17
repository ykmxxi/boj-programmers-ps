/*
    - N x N 크기에 사탕을 채움, 사탕의 색은 모두 같지 않을 수 있음
    - 사탕의 색이 다른 인접한 두 칸을 고르고 swap
    - 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 or 열)을 골라 사탕을 먹을 때 그 최대 개수 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] adj;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                adj[i][j] = s.charAt(j);
            }
        }
    }

    static void pro() {
        int ans = 0;
        for (int x = 0; x < N - 1; x++) {
            for (int y = 0; y < N; y++) {
                swap(x, y, x + 1, y);
                ans = Math.max(ans, findMax());
                swap(x, y, x + 1, y);
            }
            if (ans == N) {
                System.out.println(ans);
                return;
            }
        }

        for (int y = 0; y < N - 1; y++) {
            for (int x = 0; x < N; x++) {
                swap(x, y, x, y + 1);
                ans = Math.max(ans, findMax());
                swap(x, y, x, y + 1);
            }
            if (ans == N) {
                System.out.println(ans);
                return;
            }
        }
        System.out.println(ans);
    }

    static void swap(int x1, int y1, int x2, int y2) {
        char tmp = adj[x1][y1];
        adj[x1][y1] = adj[x2][y2];
        adj[x2][y2] = tmp;
    }

    static int findMax() {
        int max = 0;

        // 행 탐색
        for (int i = 0; i < N; i++) {
            char prev = adj[i][0];
            int val = 1;
            for (int j = 1; j < N; j++) {
                if (prev == adj[i][j]) {
                    val++;
                    max = Math.max(max, val);
                } else {
                    val = 1;
                    prev = adj[i][j];
                }
            }
        }

        // 열 탐색
        for (int i = 0 ; i < N; i++) {
            char prev = adj[0][i];
            int val = 1;
            for (int j = 1; j < N; j++) {
                if (prev == adj[j][i]) {
                    val++;
                    max = Math.max(max, val);
                } else {
                    val = 1;
                    prev = adj[j][i];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
