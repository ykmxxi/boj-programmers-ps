/*
    - 보조 PD 모두를 만조시키는 출연 순서 정하기
    - 모두를 만족시킬 수 없으면 0을 출력
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static List<Integer>[] adj;
    static int[] indeg;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        indeg = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cnt = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            int y;
            for (int j = 0; j < cnt - 1; j++) {
                y = Integer.parseInt(st.nextToken());
                adj[x].add(y);
                indeg[y]++;
                x = y;
            }
        }
    }

    static void pro() {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                dq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> ans = new ArrayList<>();
        while (!dq.isEmpty()) {
            int x = dq.poll();
            ans.add(x);

            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) {
                    dq.offer(y);
                }
            }
        }

        if (ans.size() == N) {
            for (int n : ans) {
                sb.append(n).append('\n');
            }
        } else {
            sb.append(0);
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
