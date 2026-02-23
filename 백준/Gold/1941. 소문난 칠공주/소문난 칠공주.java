import java.io.*;
import java.util.*;

// 시간 복잡도: O(25C7 -> 상수 시간 내)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static char[][] adj;
    static int ans;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] selected = new int[7];

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        adj = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                adj[i][j] = line.charAt(j);
            }
        }
    }

    static void pro() {
        comb(0, 0, 0);
        System.out.print(ans);
    }

    static void comb(int idx, int len, int cnt) {
        // 가지치기: 남은 인원을 다 뽑아도 이다솜파가 4명이 안 되는 경우
        if (len + (25 - idx) < 7) {
            return;
        }
        if (len == 7) {
            if (cnt >= 4 && check()) {
                ans++;
            }
            return;
        }
        for (int i = idx; i < 25; i++) {
            selected[len] = i;
            if (adj[i / 5][i % 5] == 'S') {
                comb(i + 1, len + 1, cnt + 1);
            } else {
                comb(i + 1, len + 1, cnt);
            }
        }
    }

    static boolean check() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[25];
        boolean[] sel = new boolean[25];
        for (int num : selected) {
            sel[num] = true;
        }

        q.offer(selected[0]);
        visit[selected[0]] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            int x = cur / 5;
            int y = cur % 5;
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                int ni = nx * 5 + ny;
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                    continue;
                }
                if (visit[ni] || !sel[ni]) {
                    continue;
                }
                visit[ni] = true;
                q.offer(ni);
                cnt++;
            }
        }
        return cnt == 7;
    }
}
