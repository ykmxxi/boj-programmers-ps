import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int w, h;
	static int[][] A;
	static boolean[][] visited;
	static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}}; // 상하좌우, 양 방향 대각선

	static void input(String wh) throws IOException {
		st = new StringTokenizer(wh);
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		A = new int[h][w];
		visited = new boolean[h][w];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < w; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}

	static void dfs(int x, int y) {
		visited[x][y] = true;

		for (int k = 0; k < 8; k++) {
			int nx = x + dir[k][0];
			int ny = y + dir[k][1];

			if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
				continue;
			}
			if (visited[nx][ny]) {
				continue;
			}
			if (A[nx][ny] == 0) {
				continue;
			}

			dfs(nx, ny);
		}
	}

	static void pro() {
		int answer = 0;
		// 땅인 지점 탐색
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (A[i][j] == 1 && !visited[i][j]) {
					answer++;
					dfs(i, j);
				}
			}
		}

		sb.append(answer).append('\n');
	}

	public static void main(String[] args) throws IOException {
		String wh = " ";
		while (!(wh = br.readLine()).equals("0 0")) {
			input(wh);
			pro();
		}
		System.out.println(sb);
	}
}
