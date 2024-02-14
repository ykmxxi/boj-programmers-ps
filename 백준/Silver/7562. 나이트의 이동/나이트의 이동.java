/*
    - 한 나이트가 놓여져 있을 때 이동하려는 칸으로 몇 번 움직이면 이동 가능한가
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int l, sx, sy, dx, dy;
    static int[][] dir = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};
    static int[][] dist;

    static void input() throws IOException {
        l = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());

        dist = new int[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                dist[i][j] = -1;
            }
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        dist[sx][sy] = 0;

        q.add(sx);
        q.add(sy);

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 8; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= l || ny >= l) {
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
        bfs();

        sb.append(dist[dx][dy]).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            input();
            pro();
        }

        System.out.print(sb);
    }

}
