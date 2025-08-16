// 스타트링크: https://www.acmicpc.net/problem/5014
// 시간 복잡도: O(V + E) -> 1 ~ 10^6층, U,D 모두 1이라면 -> O(2 * 10^6)

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int F, S, G, U, D;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dist = new int[F + 1]; // 1층 ~ F층
        Arrays.fill(dist, -1);
    }

    static void pro() {
        // 총 F층, G층에 위치. S -> G, 버튼 2개 존재(U, D)
        // G층에 도착하려면 적어도 몇 번 눌러야 하는가? 최소 값 탐색 -> bfs
        String str = "use the stairs";
        bfs(S);

        if (dist[G] == -1) {
            System.out.println(str);
        } else {
            System.out.println(dist[G]);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int curUp = cur + U;
            int curDown = cur - D;

            if (curUp >= 1 && curUp <= F && dist[curUp] == -1) {
                q.offer(curUp);
                dist[curUp] = dist[cur] + 1;
            }
            if (curDown >= 1 && curDown <= F && dist[curDown] == -1) {
                q.offer(curDown);
                dist[curDown] = dist[cur] + 1;
            }
            if (curUp == G || curDown == G) {
                break;
            }
        }
    }
}
