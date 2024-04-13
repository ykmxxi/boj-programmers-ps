/*
    - 노드 하나를 지울 때 남은 트리에서 리프 노드의 개수를 구하는 프로그램
    - 리프 노드 -> 인접한 점이 오직 부모만 있을 때
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, root, remove;
    static List<Integer>[] child;
    static int[] leaf;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        child = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            child[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        leaf = new int[N];
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == -1) {
                root = i;
                continue;
            }
            child[n].add(i); // n의 자식은 i
        }

        remove = Integer.parseInt(br.readLine());
    }

    static void dfs(int x, int par) {
        if (child[x].isEmpty()) {
            leaf[x] = 1;
        }

        for (int y : child[x]) {
            if (y == par) {
                continue;
            }

            dfs(y, x);
            leaf[x] += leaf[y];
        }
    }

    static void pro() {
        for (int i = 0; i < N; i++) {
            if (child[i].contains(remove)) {
                child[i].remove(child[i].indexOf(remove));
            }
        }

        if (root != remove) {
            dfs(root, -1);
        }

        System.out.println(leaf[root]);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
