/*
    - 상사(부모)가 직속 부하(자식)를 칭찬하면 그 부하의 직속 부하를 연쇄적으로 칭찬
    - 모든 칭찬에 칭찬의 정도가 존재하고, 이 수치 또한 부하들에게 똑같이 적용
    - 각자 얼마나 칭찬 받았는지 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] ans;
    static List<Integer>[] child;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         child = new ArrayList[n + 1];
         st = new StringTokenizer(br.readLine(), " ");
         for (int i = 1; i <= n; i++) {
             child[i] = new ArrayList<>();
         }
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                continue;
            }
            child[parent].add(i);
        }

        ans = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ans[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }
    }

    static void dfs(int x) {
        for (int y : child[x]) {
            ans[y] += ans[x];
            dfs(y);
        }
    }

    static void pro() {
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(' ');
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
