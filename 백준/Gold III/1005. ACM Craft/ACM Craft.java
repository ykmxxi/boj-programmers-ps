import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, K, W;
	static ArrayList<Integer>[] adj;
	static int[] indeg, T_done, T;

	static void input() throws IOException {
		// 테스트 케이스가 존재하는 문제이기 때문에 배열 초기화에 유의
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		indeg = new int[N + 1];
		T = new int[N + 1];
		T_done = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			T[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			indeg[y]++;
		}
		W = Integer.parseInt(br.readLine());
	}

	static void pro() {
		Deque<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (indeg[i] == 0) {
				queue.add(i);
				T_done[i] = T[i];
			}
		}

		while (!queue.isEmpty()) {
			int x = queue.poll();
			for (int y : adj[x]) {
				indeg[y]--;
				if (indeg[y] == 0) {
					queue.add(y);
				}
				T_done[y] = Math.max(T_done[y], T_done[x] + T[y]);
			}
		}
		sb.append(T_done[W]).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		while (T > 0) {
			T--;
			input();
			pro();
		}
		System.out.println(sb);
	}
}
