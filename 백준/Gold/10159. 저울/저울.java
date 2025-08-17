// 저울: https://www.acmicpc.net/problem/10159
// 시간 복잡도: O(N^3) -> 불리언 전이 폐쇄(Floyd–Warshall)

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static boolean[][] reach; // reach[u][v] = (u > v 를 알 수 있음)

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine()); // 물건(정점) 개수
        M = Integer.parseInt(br.readLine()); // 비교(간선) 개수

        reach = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // a > b
            int b = Integer.parseInt(st.nextToken());
            reach[a][b] = true; // a가 더 무거움(a -> b)
        }
    }

    static void pro() {
        floyd(); // 전이 폐쇄

        for (int i = 1; i <= N; i++) {
            int heavier = 0; // j > i 를 알 수 있는 j 개수 = 열 i에서 true 개수
            int lighter = 0; // i > j 를 알 수 있는 j 개수 = 행 i에서 true 개수

            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (reach[i][j]) lighter++;
                if (reach[j][i]) heavier++;
            }

            int unknown = (N - 1) - lighter - heavier;
            sb.append(unknown).append('\n');
        }

        System.out.print(sb);
    }

    // Floyd–Warshall(불리언): i->k, k->j 가 되면 i->j 도 된다
    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (!reach[i][k]) continue; // 미세 최적화
                for (int j = 1; j <= N; j++) {
                    if (reach[k][j]) reach[i][j] = true;
                }
            }
        }
    }
}
