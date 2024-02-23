/*
    - 작업 N개(3 ~ 10,000), 작업마다 걸리는 시간(1 ~ 100) -> 최악의 경우 10^6
    - 선행 작업이 존재(K번 작업 선행 작업들의 번호는 1 ~ K - 1)
    - 선행 관계가 없는 작업이 반드시 하나 이상 존재(1번 작업은 항상 X)
    - 모든 작업을 완료하기 위해 필요한 최소 시간(선행 관계가 없는 작업들은 동시 수행 가능)
 */

import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<Integer>[] adj;
    static int[] time, indeg, done;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        time = new int[N + 1];
        indeg = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());

            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int k = Integer.parseInt(st.nextToken());
                adj[i].add(k);
                indeg[k]++;
            }
        }
    }

    static void pro() {
        int ans = 0;
        done = new int[N + 1];
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                dq.offer(i);
                done[i] = time[i];
            }
        }

        while (!dq.isEmpty()) {
            int x = dq.poll();

            for (int y : adj[x]) {
                indeg[y]--;

                if (indeg[y] == 0) {
                    dq.offer(y);
                }
                done[y] = Math.max(done[x] + time[y], done[y]);
            }
            ans = Math.max(ans, done[x]);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
