import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[] arr;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
    }

    static void rec(int depth) {
        if (depth == M) {
            for (int a : arr) {
                sb.append(a).append(' ');
            }
            sb.append('\n');
            return;
        }
        int start = 0;
        if (depth != 0) {
            start = arr[depth - 1];
        }
        for (int cand = start + 1; cand <= N; cand++) {
            arr[depth] = cand;
            rec(depth + 1);
            arr[depth] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        rec(0);
        System.out.print(sb.toString());
    }

}
