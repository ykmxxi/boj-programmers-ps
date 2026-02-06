import java.io.*;
import java.util.*;

// 시간 복잡도: O(3 * 100,000)
// 공간 복잡도: O(200,000)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K;
    static int[] prev;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        // 수빈이가 동생을 찾을 수 있는 가장 빠른 시간, 경로(백트래킹)
        prev = new int[200005];
        dist = new int[200005];
        Arrays.fill(prev, -1);
        Arrays.fill(dist, -1);

        bfs();

        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(K);
        while (true) {
           int cur = dq.peekFirst();
           if (cur == N) {
               break;
           }
           dq.push(prev[cur]);
        }

        sb.append(dist[K]).append('\n');
        while (!dq.isEmpty()) {
            sb.append(dq.poll()).append(' ');
        }
        System.out.print(sb);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        prev[N] = 0;
        dist[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) {
                break;
            }

            int[] arr = {cur + 1, cur - 1, cur * 2};
            for (int next : arr) {
                if (next < 0 || next >= 200000) {
                    continue;
                }
                if (dist[next] > -1) {
                    continue;
                }
                q.offer(next);
                dist[next] = dist[cur] + 1;
                prev[next] = cur;
            }
        }
    }
}
