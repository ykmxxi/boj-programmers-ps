// 치즈: https://www.acmicpc.net/problem/2636
// 시간 복잡도: O(시간 * NM) -> 최악의 경우 NM^2인듯 -> 10^8 -> 1초 이내 해결 가능

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[][] arr, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;
    static int N, M, remain;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    remain++;
                }
            }
        }
    }

    static void pro() {
        // 격자형 배열, 가장자리는 치즈가 없음, 치즈에는 하나 이상의 구멍이 존재
        // 공기와 접촉된 칸은 1시간 뒤 녹아 없어짐, 치즈가 모두 녹아 없어지는데 걸리는 시간, 모두 녹기 한 시간 전 개수
        int lastRemain = remain;
        int time = 0;
        while (remain > 0) {
            visit = new boolean[N][M];
            // (0, 0) 가장 자리에서 시작, 구멍은 가장 자리에서 시작해 들어가지 못함 -> 굳이 체크할 필요 없음
            int deleted = bfs(0, 0);
            time++;
            remain -= deleted;
            if (remain != 0) {
                lastRemain = remain;
            }
        }
        sb.append(time).append('\n').append(lastRemain);
        System.out.print(sb);
    }

    static int bfs(int sx, int sy) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(sx);
        q.offer(sy);
        visit[sx][sy] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (visit[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    arr[nx][ny] = 0;
                    cnt++;
                    continue;
                }
                q.offer(nx);
                q.offer(ny);
                visit[nx][ny] = true;
            }
        }
        return cnt;
    }
}
