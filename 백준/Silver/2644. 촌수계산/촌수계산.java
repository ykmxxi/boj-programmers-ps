/*
    -
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n, m, start, dest;
    static List<Integer>[] adj;
    static int[] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = -1;
        }
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int y : adj[x]) {
                if (dist[y] != -1) {
                    continue;
                }
                dist[y] = dist[x] + 1;
                q.add(y);
            }
        }
    }

    static void pro() {
        bfs();

        System.out.println(dist[dest]);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
