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

	static int K, M, P; // 테스트 케이스 번호, 노드의 수, 간선의 수
	static ArrayList<Integer>[] adj;
	static int[] indeg, strahler;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		adj = new ArrayList[M + 1];
		indeg = new int[M + 1];
		strahler = new int[M + 1];

		for (int i = 1; i <= M; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= P; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adj[x].add(y);
			indeg[y]++;
		}
	}

	static void pro() {
		Deque<Integer> que = new LinkedList<>();
		for (int i = 1; i <= M; i++) {
			if (indeg[i] == 0) {
				que.add(i);
				strahler[i] = 1;
			}
		}

		while (!que.isEmpty()) {
			int x = que.poll();

			for (int y : adj[x]) {
				indeg[y]--;
				if (strahler[y] == 0) {
					strahler[y] = strahler[x];
				} else {
					int ans = 0, cnt = 0;
					for (int i = 1; i <= M; i++) {
						if (adj[i].contains(y)) {
							ans = Math.max(ans, strahler[i]);
						}
					}
					for (int i = 1; i <= M; i++) {
						if (adj[i].contains(y) && strahler[i] == ans) {
							cnt++;
						}
					}
					if (cnt > 1) {
						strahler[y] = ans + 1;
					} else {
						strahler[y] = ans;
					}
				}

				if (indeg[y] == 0) {
					que.add(y);
				}
			}
		}
		sb.append(K).append(' ').append(strahler[M]).append('\n');
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
