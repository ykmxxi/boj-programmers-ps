/*
    - 루트가 A인 트리를 전위 순회, 중위 순회, 후위 순회 결과를 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Character>[] adj;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String par = st.nextToken();
            adj[par.charAt(0) - 'A'].add(st.nextToken().charAt(0));
            adj[par.charAt(0) - 'A'].add(st.nextToken().charAt(0));
        }

    }

    static void pre_dfs(char x) { // 루트, 왼쪽, 오른쪽
        if (x == '.') {
            return;
        }
        sb.append(x);
        pre_dfs(adj[x - 'A'].get(0));
        pre_dfs(adj[x - 'A'].get(1));

    }

    static void mid_dfs(char x) { // 왼쪽, 루트, 오른쪽
        if (x == '.') {
            return;
        }
        mid_dfs(adj[x - 'A'].get(0));
        sb.append(x);
        mid_dfs(adj[x - 'A'].get(1));
    }

    static void post_dfs(char x) {
        if (x == '.') {
            return;
        }
        post_dfs(adj[x - 'A'].get(0));
        post_dfs(adj[x - 'A'].get(1));
        sb.append(x);
    }

    static void pro() {
        pre_dfs('A');
        sb.append('\n');

        mid_dfs('A');
        sb.append('\n');

        post_dfs('A');

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
