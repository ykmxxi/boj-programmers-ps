/*
    - 루트 트리, 정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, R, Q;
    static int[] query, dy;
    static List<Integer>[] adj;
    static boolean[] visit;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
            adj[y].add(x);
        }

        query = new int[Q];
        for (int i = 0; i < Q; i++) {
            query[i] = Integer.parseInt(br.readLine());
        }
    }

    static void dfs(int x) {
        visit[x] = true;
        dy[x]++; // 자신 추가

        for (int y : adj[x]) {
            if (visit[y]) {
                continue;
            }

            dfs(y);

            dy[x] += dy[y]; // dfs를 끝낸 자신의 서브 트리의 개수 추가
        }
    }

    static void pre() {
        dy = new int[N + 1];
        visit = new boolean[N + 1];

        dfs(R); // Root 부터
    }

    static void pro() {
        StringBuilder sb = new StringBuilder();
        for (int q : query) {
            sb.append(dy[q]).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pre();
        pro();
    }
}
