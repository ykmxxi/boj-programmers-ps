import java.util.*;
import java.io.*;

import org.w3c.dom.Node;

// 시간 복잡도: O(NM)
// 공간 복잡도: O(NM)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] A, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                A[i][j] = line.charAt(j - 1) - '0';
            }
        }
    }

    static void pro() {
        // 격자형 배열, 0(이동 가능), 1(이동 불가 벽)
        // (1,1) -> (N,M) 최단 경로. 한 점에서 다른 점까지 벽 한개만 부수고 최단 경로
        // 완탐으로 벽을 하나씩 부순 상태에서 세기? O(N^2 * M^2) -> 시간 초과 10^12

        visit = new boolean[N + 1][M + 1][2];
        bfs();

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 1, 1, false));
        visit[1][1][0] = true;
        visit[1][1][1] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == N && cur.y == M) {
                ans = Math.min(ans, cur.dist);
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dir[k][0];
                int ny = cur.y + dir[k][1];
                int nd = cur.dist + 1;

                if (nx < 1|| ny< 1|| nx>N ||ny>M) {
                    continue;
                }
                if (A[nx][ny] == 0) { // 이동 가능
                    if (cur.destroy && visit[nx][ny][1]) {
                        continue;
                    }
                    if (!cur.destroy && visit[nx][ny][0]) {
                        continue;
                    }
                    q.offer(new Node(nx, ny, nd, cur.destroy));
                    if (cur.destroy) {
                        visit[nx][ny][1] = true;
                    } else {
                        visit[nx][ny][0] = true;
                    }
                } else { // 벽일 때
                    if (cur.destroy) { // 이미 벽이 부서진 상태라면 x
                        continue;
                    }
                    if (!cur.destroy && !visit[nx][ny][1]) { // 벽을 부순적 없고 현재 벽을 부순 기록이 없다면
                        q.offer(new Node(nx, ny, nd, true));
                        visit[nx][ny][1] = true;
                    }
                }
            }
        }
    }

    static class Node {
        int x,y,dist;
        boolean destroy; // 전파 된다. 이미 벽을 부순 상태에서의 경로 노드이면 true

        Node(int x, int y, int dist, boolean destroy) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.destroy = destroy;
        }
    }
}
