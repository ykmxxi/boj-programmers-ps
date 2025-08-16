// 보물섬: https://www.acmicpc.net/problem/2589
// 시간 복잡도: O(N * M * E), E는 탐색해야 하는 육지의 개수, 최악의 경우 모두 육지라면 (NM)^2 = (25 * 10^4) -> 1초 이내

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, ans = 0;
    static int[][] dist, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }

    static void pro() {
        // 직사각형 격자배열, 육지(L), 바다(W) 육지로만 이동 가능
        // 보물: 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 묻혀있음
        // 최단 거리 -> bfs
        // 모든 육지를 시작점으로 가장 오래 걸리는 최단 거리 구해서 반환

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'W') {
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    Arrays.fill(dist[k], -1);
                }
                bfs(i, j);
            }
        }

        System.out.println(ans);
    }

    static void bfs(int sx, int sy) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(sx);
        q.offer(sy);
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (dist[nx][ny] != -1 || arr[nx][ny] == 'W') {
                    continue;
                }
                q.offer(nx);
                q.offer(ny);
                dist[nx][ny] = dist[x][y] + 1;
                ans = Math.max(ans, dist[nx][ny]);
            }
        }
    }
}
