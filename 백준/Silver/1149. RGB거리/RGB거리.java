import java.io.*;
import java.util.*;

// 시간 복잡도: O(1)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[][] A, dy;

    public static void main(String[] args) throws IOException {
            input();
            pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        pre();

        System.out.print(Math.min(dy[N][0], Math.min(dy[N][1], dy[N][2])));
    }

    static void pre() {
        // 1번 집의 색 != 2번 집의 색 -> N != N - 1
        dy = new int[N + 1][3];
        dy[1][0] = A[1][0];
        dy[1][1] = A[1][1];
        dy[1][2] = A[1][2];

        for (int i = 2; i <= N; i++) {
            dy[i][0] = Math.min(dy[i - 1][1], dy[i - 1][2]) + A[i][0];
            dy[i][1] = Math.min(dy[i - 1][0], dy[i - 1][2]) + A[i][1];
            dy[i][2] = Math.min(dy[i - 1][0], dy[i - 1][1]) + A[i][2];
        }
    }
}
