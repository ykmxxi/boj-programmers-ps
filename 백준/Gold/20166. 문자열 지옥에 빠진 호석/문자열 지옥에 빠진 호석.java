import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M, K;
	static String[] A, kStr;
	static int dx[] = {0, 0, -1, -1, -1, 1, 1, 1};
	static int dy[] = {1, -1, -1, 0, 1, -1, 0, 1};
	static Map<String, Integer> map = new HashMap<String, Integer>();

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new String[N];
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine();
		}

		kStr = new String[K];
		for (int i = 0; i < K; i++) {
			kStr[i] = br.readLine();
		}
	}

	static void dfs(int x, int y, String path, int len) {
		if (map.containsKey(path)) {
			map.put(path, map.get(path) + 1);
		} else {
			map.put(path, 1);
		}

		if (len == 5) {
			return;
		}

		for (int k = 0; k < 8; k++) {
			int nx = (x + dx[k]) % N;
			int ny = (y + dy[k]) % M;

			if (nx < 0) {
				nx += N;
			}
			if (ny < 0) {
				ny += M;
			}

			dfs(nx, ny, path + A[nx].charAt(ny), len + 1);
		}
	}

	static void pro() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, Character.toString(A[i].charAt(j)), 1);
			}
		}

		for (String K : kStr) {
			if (map.containsKey(K)) {
				sb.append(map.get(K));
			} else {
				sb.append(0);
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
