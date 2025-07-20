import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[] arr;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m + 1];
    }

    static void rec_func(int k) {
        if (k == m + 1) { // m개를 다 고른 경우
            for (int i = 1; i <= m; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int cand = 1; cand <= n; cand++) {
                arr[k] = cand;
                rec_func(k + 1);
                arr[k] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }

}