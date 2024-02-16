/*
    - N * M 크기 격자형 그래프, 상하좌우 이동가능, 빈 곳 or 벽이 존재 -> 벽은 부숴야 이동 가능
    - 벽을 부수면 빈 방과 동일한 방으로 변경
    - (1, 1) -> (N, M) 이동하려고 할 때, 벽을 최소 몇 개 부수어야 하는지 구하기
    - 최단 거리 이동할 때 부숴야 하는 벽 최소 개수 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static String[] A;
    static int[][] cnt, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
        cnt = new int[N][M];
    }

    static void dijkstra(int sx, int sy) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(sx, sy,  0));
        cnt[sx][sy] = 0;

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            for (int k = 0; k < 4; k++) {
                int nx = info.x + dir[k][0];
                int ny = info.y + dir[k][1];
                int nc = info.count;

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (A[nx].charAt(ny) == '1') {
                    nc++;
                }
                if (cnt[nx][ny] > nc) {
                    cnt[nx][ny] = nc;
                    pq.add(new Info(nx, ny, nc));
                }
            }
        }
    }

    static void pro() {
        dijkstra(0, 0);

        if (cnt[N - 1][M - 1] == -1) {
            System.out.println(0);
        } else {
            System.out.println(cnt[N - 1][M - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static class Info implements Comparable<Info> {
        int x;
        int y;
        int count;

        Info(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Info o) {
            return this.count - o.count;
        }
    }
}
