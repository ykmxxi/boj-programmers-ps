/*
    - N x N 체스판 위 나이트가 M개의 말들을 잡기 위한 최소 이동 수를 계산
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, sx, sy;
    static int[][] dir = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static int[][] dest, dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        dest = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            dest[i][0] = Integer.parseInt(st.nextToken());
            dest[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(sx);
        q.add(sy);
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 8; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 1 || ny < 1 || nx > N || ny > N) {
                    continue;
                }
                if (dist[nx][ny] != -1) {
                    continue;
                }

                dist[nx][ny] = dist[x][y] + 1;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void pro() {
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = -1;
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(dist[dest[i][0]][dest[i][1]]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
