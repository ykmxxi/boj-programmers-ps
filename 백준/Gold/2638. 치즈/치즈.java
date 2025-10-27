import java.util.*;
import java.io.*;

// 시간 복잡도:
// 공간 복잡도:

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, ans = 0, cnt = 0;
    static int[][] A, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] == 1) {
                    cnt++;
                }
            }
        }
    }

    static void pro() {
        while (true) {
            if (cnt == 0) break;

            visit = new boolean[N][M];
            findExternal(0, 0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (A[i][j] == 1 && can(i, j)) {
                        cnt--;
                        A[i][j] = 0;
                    }
                }
            }
            
            ans++;
        }

        System.out.print(ans);
    }

    static void findExternal(int x, int y) {
        visit[x][y] = true;
        A[x][y] = -1;

        for (int k = 0; k<4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if (nx <0 || ny < 0 || nx >= N || ny>= M) {
                continue;
            }
            if (visit[nx][ny] || A[nx][ny] == 1) {
                continue;
            }
            findExternal(nx, ny);
        }
    }

    static boolean can(int x, int y) {
        int ec = 0;
        for (int k = 0; k < 4; k++) {
            if (A[x + dir[k][0]][y + dir[k][1]] == -1) {
                ec++;
            }
        }
        return ec >= 2;
    }
}
