import java.io.*;
import java.util.*;

// 시간 복잡도: O(1)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] adj;
    static boolean[][] visit;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int ans = 0;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        adj = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                adj[i][j] = line.charAt(j);
            }
        }
    }

    static void pro() {
        // 여러 색깔의 뿌요를 놓는다 -> 다른 뿌요가 나올때까지 아래로 떨어진다
        // 같은 색 뿌요가 4개 이상 상하좌우 연결되면 한꺼번에 없어진다
        // 터질 수 있는 뿌요가 여러 그룹이면 동시에 터지고 한번의 연쇄가 추가된다
        while (true) {
            flag = false;
            visit = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (adj[i][j] == '.' || visit[i][j]) { // 빈 공간이거나, 이미 방문한 점은 넘어가
                        continue;
                    }
                    ppuyo(i, j, adj[i][j]);
                }
            }

            // 아래로 내리기
            down();

            if (flag) {
                ans++;
            } else {
                break;
            }
        }

        System.out.print(ans);
    }

    static void ppuyo(int sx, int sy, char color) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> list = new ArrayList<>();
        q.offer(new int[]{sx,sy});
        list.add(new int[]{sx,sy});
        visit[sx][sy] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
                    continue;
                }
                if (visit[nx][ny] || adj[nx][ny] != color) {
                    continue;
                }
                visit[nx][ny] = true;
                q.offer(new int[]{nx, ny});
                list.add(new int[]{nx,ny});
                cnt++;
            }
        }

        if (cnt >= 4) {
            flag = true;
            for (int i = 0; i < list.size(); i++) {
                int[] pos = list.get(i);
                adj[pos[0]][pos[1]] = '.';
            }
        }
    }

    static void down() {
        for (int col = 0; col < 6; col++) { // 한 col(열) 씩 돌면서 밑으로 내리기
            Deque<Character> stack = new ArrayDeque<>();
            for (int row = 0; row < 12; row++) {
                if (adj[row][col] != '.') {
                    stack.push(adj[row][col]);
                }
            }

            for (int row = 11; row >= 0; row--) {
                if (!stack.isEmpty()) {
                    adj[row][col] = stack.pop();
                } else {
                    adj[row][col] = '.';
                }
            }
        }
    }
}
