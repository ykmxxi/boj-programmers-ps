import java.io.*;
import java.util.*;

// 시간 복잡도: O(N!)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, ans = 0, cnt = 0;
    static int[][] A; // i번 계란의 내구도, 무게
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N][2];
        broken = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        // 내구도, 무게
        // 내구도 = 현재 내구도 - 상대 계란의 무게, 0이하가 되는 순간 깨진다
        rec(0);

        System.out.print(ans);
    }

    static void rec(int cur) {
        if (cur == N) {
            ans = Math.max(ans, cnt);
            return;
        }
        if (broken[cur] || cnt == N - 1) {
            rec(cur + 1);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (i == cur || broken[i]) { // 현재 계란이 깨지지 않았다면
                continue;
            }
            A[i][0] -= A[cur][1];
            A[cur][0] -= A[i][1];
            if (A[i][0] <= 0) {
                broken[i] = true;
                cnt++;
            }
            if (A[cur][0] <= 0) {
                broken[cur] = true;
                cnt++;
            }
            rec(cur + 1);

            if (A[i][0] <= 0) {
                broken[i] = false;
                cnt--;
            }
            if (A[cur][0] <= 0) {
                broken[cur] = false;
                cnt--;
            }
            A[i][0] += A[cur][1];
            A[cur][0] += A[i][1];
        }
    }
}
