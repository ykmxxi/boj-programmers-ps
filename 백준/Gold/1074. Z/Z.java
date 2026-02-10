import java.io.*;
import java.util.*;

// 시간 복잡도: O(N)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, r, c;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        System.out.print(rec(N , r, c));
    }

    static int rec(int n, int r, int c) {
        if (n == 0) {
            return 0;
        }
        int half = 1 << (n - 1);
        if (r < half && c < half) { // 1사분면
            return rec(n - 1, r, c);
        }
        if (r < half && c >= half) { // 2사분면
            return half * half + rec(n - 1, r, c - half);
        }
        if (r >= half && c < half) { // 3사분면
            return 2 * half * half + rec(n - 1, r - half, c);
        }
        // 4사분면
        return 3 * half * half + rec(n - 1, r - half, c- half);
    }
}
