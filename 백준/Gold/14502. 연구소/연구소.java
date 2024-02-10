/*
    - 바이러스는 상하좌우로 퍼짐
    - 새로 세울 수 있는 벽의 개수는 3개
    - 0은 빈 칸, 1은 벽, 2는 바리어스
    - 벽을 3개 세운 뒤 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 할 때, 안정 영역 크기의 최대값 구하기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, B, ans;
    static int[][] A, blank, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] == 2) {
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (A[nx][ny] != 0) {
                    continue;
                }
                if (visit[nx][ny]) {
                    continue;
                }

                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] == 0 && !visit[i][j]) {
                    cnt++;
                }
            }
        }

        ans = Math.max(ans, cnt);
    }

    static void dfs(int idx, int selected_cnt) {
        if (selected_cnt == 3) {
            bfs();
            return;
        }
        if (idx > B) {
            return;
        }

        A[blank[idx][0]][blank[idx][1]] = 1; // 벽 세우기
        dfs(idx + 1, selected_cnt + 1); // 다음 벽 세우기

        A[blank[idx][0]][blank[idx][1]] = 0; // 벽 안세우고 넘어가기
        dfs(idx + 1, selected_cnt);
    }

    static void pro() {
        // 먼저 벽을 세울 수 있는 위치를 기록(빈 칸인 공간 저장)
        blank = new int[N * M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] == 0) {
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }
        dfs(1, 0);

       System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
