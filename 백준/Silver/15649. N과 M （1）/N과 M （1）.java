// https://www.acmicpc.net/problem/15649
// 순열
// 시간 복잡도: O(N! / (N - M)!) -> 최악의 경우 8개를 고르면? (8! / 0!)

import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] sel;
    static boolean[] visited;

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
        visited = new boolean[N + 1];
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
            for (int cand = 1; cand<=N; cand++) {
                if (visited[cand]) { // 이미 고른 숫자라면 중복 X
                    continue;
                }
                sel[depth] = cand;
                visited[cand] = true;

                rec(depth + 1);

                // 초기화
                sel[depth] = 0;
                visited[cand] = false;
            }
        }
    }
}
