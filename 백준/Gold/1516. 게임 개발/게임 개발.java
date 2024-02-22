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

	static int N;
	static int[] indeg, time, done;
	static ArrayList<Integer>[] adj;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		indeg = new int[N + 1];
		time = new int[N + 1];
		done = new int[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 첫 번째: 건물을 짓는데 걸리는 시간, 두 번째 ~ -1 전 까지: 선행 건물 번호, -1: 종료
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;

			while (st.hasMoreTokens()) {
				int x = Integer.parseInt(st.nextToken());
				if (x != -1) {
					adj[x].add(i);
					indeg[i]++;
				}
			}
		}
	}

	static void pro() {
		// 위상 정렬
		Deque<Integer> que = new LinkedList<>();

		for (int i = 1; i <= N; i++) { // 제일 먼저 지을 수 있는 건물 찾기
			if (indeg[i] == 0) {
				done[i] = time[i];
				que.add(i);
			}
		}

		while (!que.isEmpty()) {
			int x = que.poll();

			for (int y : adj[x]) {
				indeg[y]--;
				if (indeg[y] == 0) {
					que.add(y);
				}
				done[y] = Math.max(done[y], done[x] + time[y]);
			}
		}

		// 정답 계산
		for (int i = 1; i <= N; i++) {
			sb.append(done[i]).append('\n');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
