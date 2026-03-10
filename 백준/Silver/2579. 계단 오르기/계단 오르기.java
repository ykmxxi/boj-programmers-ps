import java.io.*;
import java.util.*;

// 시간 복잡도: O(1)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[][] dy;
    static int[] A;

    public static void main(String[] args) throws IOException {
            input();
            pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        pre();

        System.out.print(Math.max(dy[N][0], dy[N][1]));
    }

    static void pre() {
        // 한 번에 한 계단씩 or 두 계단
        // 연속된 세 개의 계단을 모두 밟으면 안됨
        // 마지막 계단은 반드시 밟기
        dy = new int[N + 1][2]; // [n][0] -> 이전 계단을 안 밟음, [n][1] -> 이전 계단을 밟음
        dy[1][0] = A[1];
        dy[1][1] = A[1];

        for (int i = 2; i <= N; i++) {
            dy[i][0] = Math.max(dy[i - 2][0], dy[i - 2][1]) + A[i]; // 이전 계단 X, 2계단 전 중에 그 전 것을 밟든 안 밟든 큰거
            dy[i][1] = dy[i - 1][0] + A[i]; // 이전 계단 O, 이전 계단을 밟았으면 이전 계단에서 바로 전 계단을 안 밟은것을 선택
        }
    }
}
