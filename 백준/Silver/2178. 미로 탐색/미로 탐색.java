/*
    - N x M 배열의 미로에서 1은 이동 가능, 0은 이동 불가능
    - (1, 1) 출발해 (N, M) 위치로 이동할 때 지나야 하는 최소의 칸 수
    - 인접(상하좌우)한 칸으로만 이동 가능
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static String[] A;
    static int[][] dist, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }

        dist = new int[N][M];
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(0);
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (A[nx].charAt(ny) == '0' || dist[nx][ny] != 0) {
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

        System.out.println(dist[N - 1][M - 1]);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
