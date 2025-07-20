// https://www.acmicpc.net/problem/15652
// 중복 조합
// 시간 복잡도: O(N^M) 보다는 적음, 즉 중복 순열보다 시간 복잡도는 조금 낮으나 거의 비슷

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
        System.out.println(sb);
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
            for (int i = 0 ; i < M; i++) {
                sb.append(sel[i]).append(' ');
            }
            sb.append('\n');
        } else {
            int start = 1; // 첫 선택은 1부터 시작
            if (depth != 0) {  // 첫 선택이 아니라면
                start = sel[depth - 1]; // 이전에 선택한 값 부터 시작
            }
            for (int cand = start; cand <= N; cand++) {
                sel[depth] = cand;

                rec(depth + 1);

                sel[depth] = 0;
            }
        }
    }
}
