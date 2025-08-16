// 단지번호붙이기: https://www.acmicpc.net/problem/2667
// 시간 복잡도: O(N^2 logN) -> N의 최대값은 5^2 -> 5^4 -> O(1)에 가까움 상수 시간에

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, size;
    static int[][] arr, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < ch.length; j++) {
                arr[i][j] = ch[j] - '0';
            }
        }
    }

    static void pro() {
        // 1은 집, 0은 빈 곳 -> 단지의 수를 오름차순으로 정렬해 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0 || visit[i][j]) {
                    continue;
                }
                size = 0;
                dfs(i, j);
                ans.add(size);
            }
        }

        Collections.sort(ans);
        sb.append(ans.size()).append('\n');
        for (int a : ans) {
            sb.append(a).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        size++;

        for (int k = 0; k < 4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (visit[nx][ny] || arr[nx][ny] == 0) {
                continue;
            }
            dfs(nx, ny);
        }
    }
}
