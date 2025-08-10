// 블록 쌓기: https://www.acmicpc.net/problem/2583
// 시간 복잡도: O(N*M) -> 영역 리스트 정렬은 거의 의미 없을듯

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, K, size;
    static int[][] A;
    static boolean[][] visit;
    static List<Integer> ans;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = new ArrayList<>();
        A = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int a = x1; a < x2; a++) {
                for (int b = y1; b < y2; b++) {
                    A[a][b] = 1;
                }
            }
        }
    }

    static void pro() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j] || A[i][j] == 1) {
                    continue;
                }
                size = 0;
                // dfs(i, j);
                bfs(i, j);
                ans.add(size);
            }
        }
        Collections.sort(ans);
        sb.append(ans.size()).append("\n");
        for (int a : ans) {
            sb.append(a).append(' ');
        }
        System.out.print(sb);
    }

    static void bfs(int sx, int sy) {
        visit[sx][sy] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(sx);
        q.offer(sy);

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            size++;

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (visit[nx][ny] || A[nx][ny] == 1) {
                    continue;
                }
                q.offer(nx);
                q.offer(ny);
                visit[nx][ny] = true;
            }
        }
    }

    // static void dfs(int x, int y) {
    //     visit[x][y] = true;
    //     size++;
    //
    //     for (int k = 0; k < 4; k++) {
    //         int nx = x + dir[k][0];
    //         int ny = y + dir[k][1];
    //         if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
    //             continue;
    //         }
    //         if (visit[nx][ny] || A[nx][ny] == 1) {
    //             continue;
    //         }
    //         dfs(nx, ny);
    //     }
    // }
}
