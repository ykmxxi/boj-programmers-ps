/*
    - 1 ~ N 문제집, 1번 문제가 제일 쉽고 N번이 가장 어려움
    - N 개 모두 풀어야 한다, 먼저 푸는 것이 좋은 문제가 있으면 반드시 먼저 풀기, 가능하면 쉬운 문제부터 풀기

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

        indeg = new int[N + 1];
        adj = new ArrayList[N + 1];
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
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                pq.offer(i);
            }
        }
        while (!pq.isEmpty()) {
            int x = pq.poll();
            sb.append(x).append(' ');

            for (int y : adj[x]) {
                indeg[y]--;

                if (indeg[y] == 0) {
                    pq.offer(y);
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
