/*
    - 기본 부품, 중간 부품
    - 어떤 장난감 완제품과 부품들 사이의 관계가 주어짐
    - 필요한 기본 부품의 종류별 개수를 계산
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M; // 3 ~ 100, (1 ~ N - 1): 기본 or 중간 부품, N: 완제품
    static int[] indeg;
    static int[][] cnt;
    static List<Info>[] adj;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        indeg = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            adj[y].add(new Info(x, k));
            indeg[x]++;
        }
    }

    static void pro() {
        Deque<Integer> dq = new LinkedList<>();
        cnt = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                dq.offer(i);
                cnt[i][i] = 1;
            }
        }

        while (!dq.isEmpty()) {
            int x = dq.poll();

            for (Info info : adj[x]) {
               indeg[info.num]--;

               for (int i = 1; i <= N; i++) {
                   cnt[info.num][i] += cnt[x][i] * info.cnt;
               }

               if (indeg[info.num] == 0) {
                   dq.offer(info.num);
               }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (cnt[N][i] != 0) {
                sb.append(i).append(' ').append(cnt[N][i]).append('\n');
            }
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static class Info {
        int num;
        int cnt;

        Info(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

}
