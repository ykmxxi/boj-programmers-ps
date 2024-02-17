/*
    - N x N 동굴의 (0, 0) -> (N - 1, N - 1), 소지금을 최소로 잃게 이동
    - 상하좌우 한칸씩 이동
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, cnt;
    static int[][] A, cost, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void input() throws IOException {
        A = new int[N][N];
        cost = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());

                cost[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static void dijkstra() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0, A[0][0]));
        cost[0][0] = A[0][0];

        while (!pq.isEmpty()) {
            Info info = pq.poll();
            int x = info.x;
            int y = info.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (cost[nx][ny] <= info.lose) {
                    continue;
                }
                if (A[nx][ny] + cost[x][y] >= cost[nx][ny]) {
                    continue;
                }
                cost[nx][ny] = A[nx][ny] + cost[x][y];
                pq.add(new Info(nx, ny, cost[nx][ny]));
            }
        }

    }

    static void pro() {
       dijkstra();

       sb.append("Problem ").append(cnt).append(": ").append(cost[N - 1][N - 1]).append('\n');
    }

    public static void main(String[] args) throws IOException {
        cnt = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            input();
            pro();
            cnt++;
        }

        System.out.print(sb);
    }

    static class Info implements Comparable<Info> {
        int x;
        int y;
        int lose;

        Info(int x, int y, int lose) {
            this.x = x;
            this.y = y;
            this.lose = lose;
        }

        @Override
        public int compareTo(Info o) {
            return this.lose - o.lose;
        }
    }

}
