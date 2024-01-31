import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M; // 듣도 못한 사람의 수, 보도 못한 사람의 수
    static String[] A, B, ans;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new String[N + 1];
        B = new String[M + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = br.readLine();
        }

        for (int i = 1; i <= M; i++) {
            B[i] = br.readLine();
        }
    }

    static boolean bin_search(String[] A, int L, int R, String X) {
        while (L <= R) {
            int mid = (L + R) / 2;

            if (A[mid].equals(X)) {
                return true;
            }

            if (A[mid].compareTo(X) < 0) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

    static void pro() {
        Arrays.sort(A, 1, N + 1);

        int ansCnt = 0;
        ans = new String[N + 1];

        for (int i = 1; i <= M; i++) {
            if (bin_search(A, 1, N, B[i])) {
                ans[++ansCnt] = B[i];
            }
        }

        Arrays.sort(ans, 1, ansCnt + 1);
        sb.append(ansCnt).append('\n');
        for (int i = 1; i <= ansCnt; i++) {
            sb.append(ans[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
