/*
    - N개의 정점(1 ~ N)이 있는 트리, 1번 루트, 부모-자식 관계로 자식이 없는 노드를 리프 노드
    - 처음은 모든 리프 노드에 게임말이 한개 씩 놓여 있고 차례가 오면 부모 노드로 옮긴다
    - 게임말이 루트 노드에 도착하면 그 게임말을 제거
    - 게임말이 게임판에 존재하지 않아 고를 수 없는 사람이 지게 된다
    - 성원 먼저 시작, 형석 나중
    - 성원이가 이길 수 있을지 없을지를 알려주는 문
 */

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, sum; // sum: 모든 리프 노드들의 깊이의 합
    static ArrayList<Integer>[] adj;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static void dfs(int x, int parent, int depth) {
        if (parent != -1 && adj[x].size() == 1) {
            sum += depth;
            return;
        }
        for (int y : adj[x]) {
            if (y != parent) {
                dfs(y, x, depth + 1);
            }
        }

    }

    static void pro() {
        dfs(1, -1, 0);

        if (sum % 2 == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
