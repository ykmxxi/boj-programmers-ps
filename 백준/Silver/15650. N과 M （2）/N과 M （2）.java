// https://www.acmicpc.net/problem/15650
// 조합
// 시간 복잡도: O(N! / M!(N - M)!)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] sel;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        System.out.print(sb);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sel = new int[M];
    }

    static void pro() {
        rec(0);
    }

    static void rec(int depth) {
        if (depth == M) {
            for(int i=0; i <M; i++) {
                sb.append(sel[i]).append(' ');
            }
            sb.append('\n');
        } else {
            int start = 1;
            if (depth != 0) {
                start = sel[depth - 1] + 1; // 이전 선택한 수보다 +1 -> 중복 X
            }
            for (int cand = start; cand <= N; cand++) {
                sel[depth] = cand;
                rec(depth + 1);
                sel[depth] = 0;
            }
        }
    }
}
