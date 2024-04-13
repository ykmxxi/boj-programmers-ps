import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] arr, used;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        used = new int[n + 1];
    }

    static void rec_func(int depth) {
        if (depth == m) {
            for (int a : arr) {
                sb.append(a).append(' ');
            }
            sb.append('\n');
        } else {
            for (int cand = 1; cand <= n; cand++) {
                if (used[cand] == 1) {
                    continue;
                }
                arr[depth] = cand;
                used[cand] = 1; // 사용 처리

                rec_func(depth + 1);

                arr[depth] = 0;
                used[cand] = 0; // 사용이 끝나면 초기화
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        rec_func(0);

        System.out.print(sb.toString());
    }
}