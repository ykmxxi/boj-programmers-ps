import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, A, B;
	static long C;
	static long[] d;
	static ArrayList<Info>[] adj;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[x].add(new Info(y, w));
			adj[y].add(new Info(x, w));
		}
	}

	static boolean dijkstra(long x) {
		for (int i = 1; i <= N; i++) {
			d[i] = 10000000000000001L;
		}
		d[A] = 0;

		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.add(new Info(A, 0));

		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (d[cur.idx] != cur.dist) {
				continue;
			}

			for (Info edge : adj[cur.idx]) {
				if (edge.dist > x) {
					continue;
				}

				if (d[edge.idx] > d[cur.idx] + edge.dist) {
					d[edge.idx] = d[cur.idx] + edge.dist;
					pq.add(new Info(edge.idx, d[edge.idx]));
				}
			}
		}

		return d[B] <= C;
	}

	static void pro() {
		d = new long[N + 1];
		long L = 1, R = 1000000001, answer = R;

		while (L <= R) {
			long mid = (L + R) / 2;
			if (dijkstra(mid)) {
				answer = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		if (answer == 1000000001) {
			answer = -1;
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

	static class Info implements Comparable<Info> {
		int idx;
		long dist;

		public Info() {

		}

		public Info(int idx, long dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Info other) {
			return Long.compare(this.dist, other.dist);
		}
	}

}
