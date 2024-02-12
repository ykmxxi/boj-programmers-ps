/*
    - R x C 배열, .(점): 비어있는 곳, *: 물이 차있는 지역, X: 돌, D: 비버의 굴, S: 고슴도치의 위치
    - 비버의 굴로 가능한 빨리 도망치기(최소 시간)
    - 매 분 인접한 상하좌우 중 하나로 이동 가능, 물도 매 분마다 상하좌우 인접한 비어있는 칸으로 확장
    - 물, 고슴도치 모두 돌을 통과할 수 없음
    - 다음 시간에 물이 찰 예정인 비어있는 칸으로 고슴도치 이동 불가능
    - 이동 가능하면 가장 빠른 시간 출력, 이동 불가능하면 KAKTUS 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, ansX, ansY;
    static String[] A;
    static boolean[][] visit;
    static int[][] dist, w_dist;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }

        visit = new boolean[N][M];
        dist = new int[N][M];
        w_dist = new int[N][M];
    }

    static void w_bfs() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                w_dist[i][j] = -1;

                if (A[i].charAt(j) == '*') {
                    w_dist[i][j] = 0;
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }

                if (A[i].charAt(j) == 'D') {
                    ansX = i;
                    ansY = j;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny]) {
                    continue;
                }
                if (A[nx].charAt(ny) != '.') {
                    continue;
                }

                visit[nx][ny] = true;
                w_dist[nx][ny] = w_dist[x][y] + 1;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void s_bfs() {
        visit = new boolean[N][M];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = -1;
                if (A[i].charAt(j) == 'S') {
                    dist[i][j] = 0;
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny]) {
                    continue;
                }
                if (A[nx].charAt(ny) != '.' && A[nx].charAt(ny) != 'D') {
                    continue;
                }
                if (w_dist[nx][ny] != -1 && w_dist[nx][ny] <= dist[x][y] + 1) {
                    continue;
                }

                dist[nx][ny] = dist[x][y] + 1;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void pro() {
        w_bfs();
        s_bfs();

        if (dist[ansX][ansY] == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(dist[ansX][ansY]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
