import java.io.*;
import java.util.*;

// 시간 복잡도: O(V + E) 이내
// 공간 복잡도: O(V) (V는 정점의 개수, 100,000)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K;
    static int[] dist = new int[100005];

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, -1);
    }

    static void pro() {
        // N -> N+1, N-1, 2*N
        bfs();

        System.out.print(dist[K]);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        dist[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) {
                break;
            }

            int[] arr = new int[] {cur + 1, cur - 1, cur * 2};
            for (int num : arr) {
                if (check(num)) {
                    q.offer(num);
                    dist[num] = dist[cur] + 1;
                }
            }
        }
    }

    static boolean check(int x) {
        if (x < 0 || x > 100000) {
            return false;
        }
        if (dist[x] > -1) {
            return false;
        }
        return true;
    }
}
