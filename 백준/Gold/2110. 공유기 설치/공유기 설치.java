import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C; // 집의 개수, 공유기 개수
    static int[] A;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

    }

    static void pro() {
        Arrays.sort(A);

        int L = 1;
        int R = 1000000000;
        int ans = 0;

        while (L <= R) {
            int mid = (L + R) / 2;

            if (deter(mid)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static boolean deter(int distance) {
        int cnt = 1;
        int last = A[0];

        for (int i = 1; i < N; i++) {
            if (A[i] - last >= distance) {
                last = A[i];
                cnt++;
            }
        }
        return cnt >= C;
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
