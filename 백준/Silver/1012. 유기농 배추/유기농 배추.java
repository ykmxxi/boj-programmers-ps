import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int T, M, N, K; // 테스트 케이스 수, 행, 열, 배추 개수
	static int[][] A;
	static boolean[][] visit;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			A[x][y] = 1;
		}
	}

	static void dfs(int x, int y) {
		visit[x][y] = true;

		for (int k = 0; k < 4; k++) {
			int nx = x + dir[k][0];
			int ny = y + dir[k][1];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}
			if (A[nx][ny] == 0) {
				continue;
			}
			if (visit[nx][ny]) {
				continue;
			}
			dfs(nx, ny);
		}
	}

	static void pro() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && A[i][j] == 1) {
					answer++;
					dfs(i, j);
				}
			}
		}
		sb.append(answer).append('\n');
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			input();
			pro();
		}

		System.out.println(sb);
	}
}
