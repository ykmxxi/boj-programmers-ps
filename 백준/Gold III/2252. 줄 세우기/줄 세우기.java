/*
    - N명 학생들을 키 순서대로 세우려고 한다
    - 두 학생의 키를 일부 비교한 결과를 가지고 줄을 세우기
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] indeg;
    static List<Integer>[] adj;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            indeg[to]++;
        }
    }

    static void pro() {
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                dq.add(i);
            }
        }

        while (!dq.isEmpty()) {
            int x = dq.poll();
            sb.append(x).append(' ');

            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) {
                    dq.add(y);
                }
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
