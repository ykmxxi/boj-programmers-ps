import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, answer, max;
	static int[][] A, visit, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N][M];
		visit = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, A[i][j]);
			}
		}
	}

	static void dfs(int x, int y, int sum, int cnt) {
		if (answer >= sum + (max * (4 - cnt))) {
			return;
		}
		if (cnt == 4) {
			answer = Math.max(answer, sum); // 최대값 갱신
		} else {
			for (int k = 0; k < 4; k++) {
				int nx = x + dir[k][0];
				int ny = y + dir[k][1];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				if (visit[nx][ny] == 1) {
					continue;
				}

				if (cnt == 2) { // ㅜ 모양 테트로미노는 2번째 점에서 상화좌우 탐색이 아님
					visit[nx][ny] = 1;
					dfs(x, y, sum + A[nx][ny], cnt + 1); // 두 번째 점에서 다시 탐색
					visit[nx][ny] = 0;
				}

				visit[nx][ny] = 1;
				dfs(nx, ny, sum + A[nx][ny], cnt + 1);
				visit[nx][ny] = 0;
			}
		}
	}

	static void pro() {
		// 시작점을 기준으로 상하좌우 중 이동 3번을 통해 테트로미노가 완성됨
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = 1;
				dfs(i, j, A[i][j], 1);
				visit[i][j] = 0;
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
