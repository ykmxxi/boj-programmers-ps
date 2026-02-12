import java.io.*;
import java.util.*;

// 시간 복잡도: O(N!)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, cnt = 0;
    static boolean[] check1; // 열(col) 확인
    static boolean[] check2; // 좌하/우상 대각선 체크
    static boolean[] check3; // 좌상/우하 대각선 체크

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        check1 = new boolean[N];
        check2 = new boolean[2*N];
        check3 = new boolean[2*N];
    }

    static void pro() {
        rec(0);

        System.out.print(cnt);
    }

    static void rec(int row) {
        if (row == N) {
            cnt++;
            return;
        }
        for (int col = 0; col < N; col++) {
            if (check1[col] || check2[row + col] || check3[row - col + N - 1]) {
                continue;
            }
            check1[col] = true;
            check2[row + col] = true;
            check3[row - col + N - 1] = true;

            rec(row + 1);

            check1[col] = false;
            check2[row + col] = false;
            check3[row - col + N - 1] = false;
        }
    }
}
