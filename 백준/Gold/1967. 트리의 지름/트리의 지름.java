// 트리의 지름: https://www.acmicpc.net/problem/1967
// 시간 복잡도: O(n)

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, ans = 0, far = 1;
    static List<Node>[] adj;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[x].add(new Node(y, w));
            adj[y].add(new Node(x, w));
        }
    }

    static void pro() {
        // 두 노드 사이의 경로의 길이 -> 트리의 지름

        visit = new boolean[n + 1];
        dfs(1, 0); // 가장 높은 가중치를 갖는 간선의 마지막 정점을 탐색하는 dfs

        visit = new boolean[n + 1];
        dfs(far, 0); // 가장 높은 가중치를 갖는 간선의 마지막 정점에서 시작

        System.out.println(ans);
    }

    static void dfs(int x, int val) {
        if (val > ans) {
            ans = val;
            far = x;
        }
        visit[x] = true;

        for (Node cur : adj[x]) {
            int y = cur.v;
            if (visit[y]) {
                continue;
            }
            dfs(y, cur.w + val);
        }
    }

    static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
