import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M, oCnt, vCnt;
	static String[] A;
	static boolean[][] visit;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new String[N];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			A[i] = br.readLine();
		}
	}

	static void dfs(int x, int y) {
		if (A[x].charAt(y) == 'o') {
			oCnt++;
		}
		if (A[x].charAt(y) == 'v') {
			vCnt++;
		}
		visit[x][y] = true;

		for (int k = 0; k < 4; k++) { // 상하좌우 탐색
			int nx = x + dir[k][0];
			int ny = y + dir[k][1];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}
			if (visit[nx][ny]) {
				continue;
			}
			if (A[nx].charAt(ny) == '#') {
				continue;
			}
			dfs(nx, ny);
		}
	}

	static void pro() {
		int totalO = 0;
		int totalV = 0;

		// 울타리가 아닌 영역을 찾아 탐색 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i].charAt(j) != '#' && !visit[i][j]) {
					oCnt = 0;
					vCnt = 0;
					dfs(i, j);
					if (oCnt > vCnt) {
						totalO += oCnt;
					} else {
						totalV += vCnt;
					}
				}
			}
		}

		sb.append(totalO).append(' ').append(totalV);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
