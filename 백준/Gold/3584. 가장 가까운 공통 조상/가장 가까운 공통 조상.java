/*
    - 루트 트리의 두 정점이 주어질 때 그들의 가장 가까운 공통 조상: 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은 노드
    - 즉, 두 노드에 가장 가까운 조상 노드
 */

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, A, B;
    static int[] parent;
    static boolean[] visit;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        visit = new boolean[N + 1];
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            parent[y] = x;
        }
        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        while (A > 0) { // A의 조상을 체크
            visit[A] = true;
            A = parent[A];
        }

        while (B > 0 && !visit[B]) { // B의 조상을 보면서 A의 조상과 겹치는 순간까지 반복
            B = parent[B];
        }

        sb.append(B).append('\n');
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            input();
            pro();
        }

        System.out.print(sb);
    }

}
