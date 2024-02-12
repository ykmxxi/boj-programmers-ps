import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, K;
	static int[] dist;
	static boolean[] visit;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new int[100001];
		visit = new boolean[100001];
	}

	static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.add(N);
		visit[N] = true;
		dist[N] = 0;

		while (!que.isEmpty()) {
			int x = que.poll();

			if (x - 1 >= 0 && !visit[x - 1]) {
				visit[x - 1] = true;
				dist[x - 1] = dist[x] + 1;
				que.add(x - 1);
			}
			if (x + 1 <= 100000 && !visit[x + 1]) {
				visit[x + 1] = true;
				dist[x + 1] = dist[x] + 1;
				que.add(x + 1);
			}
			if (x * 2 <= 100000 && !visit[x * 2]) {
				visit[x * 2] = true;
				dist[x * 2] = dist[x] + 1;
				que.add(x * 2);
			}
		}
	}

	static void pro() {
		bfs();
		System.out.println(dist[K]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
