import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static ArrayList<Integer>[] adj;
	static int[] parent;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			adj[y].add(x);
		}
		parent = new int[N + 1];
	}

	// parent 는 x의 부모 노드
	static void dfs(int x, int par) {
		for (int y : adj[x]) {
			if (y == par) {
				continue;
			}
			parent[y] = x;
			dfs(y, x);
		}
	}

	static void pro() {
		dfs(1, -1);
		for (int i = 2; i <= N; i++) {
			sb.append(parent[i]).append('\n');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
