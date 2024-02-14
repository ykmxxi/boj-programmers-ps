import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, T, vertex_cnt, edge_cnt;
	static ArrayList<Integer>[] graph;
	static boolean[] visit;

	static void input(String nm) throws IOException {
		st = new StringTokenizer(nm, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n + 1];
		visit = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x].add(y);
			graph[y].add(x);
		}
	}

	static void dfs(int x) {
		vertex_cnt++;
		edge_cnt += graph[x].size();
		visit[x] = true;

		for (int y : graph[x]) {
			if (visit[y]) {
				continue;
			}
			dfs(y);
		}
	}

	static void pro() {
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			if (visit[i]) {
				continue;
			}
			vertex_cnt = 0;
			edge_cnt = 0;
			dfs(i);
			if (edge_cnt == (vertex_cnt - 1) * 2) {
				answer++;
			}
		}

		sb.append("Case ").append(T).append(": ");
		if (answer == 0) {
			sb.append("No trees.\n");
		} else if (answer == 1) {
			sb.append("There is one tree.\n");
		} else {
			sb.append("A forest of ").append(answer).append(" trees.\n");
		}
	}

	public static void main(String[] args) throws IOException {
		T = 1;
		String nm = " ";
		while (!(nm = br.readLine()).equals("0 0")) {
			input(nm);
			pro();
			T++;
		}
		System.out.println(sb);
	}
}
