import java.io.*;
import java.util.*;

// 시간 복잡도: O(N * M), maxN 500, maxM 500 -> 2.5 * 10^6 -> 1초 내
// 공간 복잡도: O(N * M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, cnt = 0, max = 0;
    static int[][] adj, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        // 그림의 개수와 그림 중 넓이가 가장 넓은 것의 넓이를 출력
        // 그룹의 개수와 그룹에 포함된 노드의 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j] || adj[i][j] == 0) {
                    continue;
                }
                cnt++;
                bfs(i, j);
            }
        }

        sb.append(cnt).append('\n').append(max);
        System.out.print(sb);
    }

    static void bfs(int sx, int sy) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(sx);
        q.offer(sy);
        visit[sx][sy] = true;
        int area = 1;

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) { // 벗어나면
                    continue;
                }
                if (visit[nx][ny] || adj[nx][ny] == 0) {
                    continue;
                }
                area++;
                q.offer(nx);
                q.offer(ny);
                visit[nx][ny] = true;
            }
        }
        max = Math.max(max, area);
    }
}
