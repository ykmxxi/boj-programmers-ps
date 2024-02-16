/*
    - N(수빈이 위치, 0 ~ 10만) K(동생 위치, 0 ~ 10만)
    - 수빈이는 1초마다 -1, +1 or 0초 순간이동을 하면 *2 위치로 이동 가능
 */

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[] dist;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[100001];
    }

    static void dijkstra() {
        for (int i = 0; i <= 100000; i++) {
            dist[i] = 100000;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(N, 0));

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (dist[info.idx] < info.dist) {
                continue;
            }

            int idx1 = info.idx - 1;
            if (valid(idx1) && info.dist + 1 < dist[idx1]) {
                dist[idx1] = info.dist + 1;
                pq.add(new Info(idx1, dist[idx1]));
            }

            int idx2 = info.idx + 1;
            if (valid(idx2) && info.dist + 1 < dist[idx2]) {
                dist[idx2] = info.dist + 1;
                pq.add(new Info(idx2, dist[idx2]));
            }
            
            int idx3 = info.idx * 2;
            if (valid(idx3) && info.dist < dist[idx3]) {
                dist[idx3] = info.dist;
                pq.add(new Info(idx3, dist[idx3]));
            }
        }
    }

    static boolean valid(int idx) {
        return idx >= 0 && idx <= 100000;
    }

    static void pro() {
        dijkstra();
        if (N >= K) { // 동생이 나보다 뒤에 있으면 -1 칸씩만 움직여야 한다
            System.out.println(N - K);
        } else {
            System.out.println(dist[K]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static class Info implements Comparable<Info> {
        int idx;
        int dist;

        Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            return this.dist - o.dist;
        }
    }
}
