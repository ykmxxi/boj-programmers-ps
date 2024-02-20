import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N; // 마을 수
	static ArrayList<Integer>[] adj; // 간선 정보
	static int[] A; // 마을 주민 수
	static int[][] Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

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
	}

	static void dfs(int x, int prev) {
		Dy[x][0] = 0;
		Dy[x][1] = A[x];

		for (int y : adj[x]) {
			if (y == prev) {
				continue;
			}

			dfs(y, x);
			Dy[x][0] += Math.max(Dy[y][0], Dy[y][1]);
			Dy[x][1] += Dy[y][0];
		}
	}

	static void pro() {
		Dy = new int[N + 1][2];

		dfs(1, -1);

		System.out.println(Math.max(Dy[1][0], Dy[1][1]));
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
