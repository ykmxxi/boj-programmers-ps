import java.io.*;
import java.util.*;

// 시간 복잡도: O(K * 4 * N * M * R * C) ≈ 100 * 4 * 40 * 40 * 10 * 10 = 64,000,000
// 공간 복잡도: O(N * M)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static List<int[][]> list = new ArrayList<>();
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visit = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] adj = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < c; k++) {
                    adj[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            list.add(adj);
        }
    }

    static void pro() {
        for (int[][] sticker : list) {
            int[][] cur = sticker;
            boolean placed = false;

            for (int rot = 0; rot < 4 && !placed; rot++) {
                if (rot > 0) {
                    cur = rotate(cur);
                }
                placed = tryPlace(cur);
            }
        }

        // 붙은 칸 수 세기
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j]) count++;
            }
        }
        System.out.println(count);
    }

    static boolean tryPlace(int[][] sticker) {
        int sr = sticker.length;
        int sc = sticker[0].length;

        // 스티커 좌상단을 (i, j)에 놓았을 때
        for (int i = 0; i <= N - sr; i++) {
            for (int j = 0; j <= M - sc; j++) {
                if (canPlace(sticker, i, j)) {
                    place(sticker, i, j);
                    return true;
                }
            }
        }
        return false;
    }

    // (startR, startC)에 스티커를 붙일 수 있는지 확인
    static boolean canPlace(int[][] sticker, int startR, int startC) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1 && visit[startR + i][startC + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 실제로 스티커 붙이기
    static void place(int[][] sticker, int startR, int startC) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    visit[startR + i][startC + j] = true;
                }
            }
        }
    }

    // 시계 방향 90도 회전: (i, j) → (j, n-1-i)
    static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotated = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[i][j] = arr[n - 1 - j][i];
            }
        }
        return rotated;
    }
}
