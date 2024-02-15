import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, H;
	static int[][][] A, dist; // H(높이), M(행), N(열)
	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int[][] dir = {{1, 0, 0}, {-1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {0, 1, 0}, {0, -1, 0}};

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		H = Integer.parseInt(st.nextToken()); // 높이

		A = new int[H + 1][N + 1][M + 1];
		dist = new int[H + 1][N + 1][M + 1];

		for (int h = 1; h <= H; h++) {
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= M; j++) {
					A[h][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
	}

	static void bfs() { // Multi-source BFS
		Queue<Integer> que = new LinkedList<>();

		// TODO: A[h][i][j] = 1인 시작점 탐색, dist 배열 초기화
		for (int h = 1; h <= H; h++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					dist[h][i][j] = -1;

					if (A[h][i][j] == 1) {
						que.add(h);
						que.add(i);
						que.add(j);
						dist[h][i][j] = 0;
					}

				}
			}
		}

		while (!que.isEmpty()) {
			int z = que.poll();
			int x = que.poll();
			int y = que.poll();

			for (int k = 0; k < 6; k++) {
				int nz = z + dir[k][0];
				int nx = x + dir[k][1];
				int ny = y + dir[k][2];

				if (nz < 1 || nx < 1 || ny < 1 || nz > H || nx > N || ny > M) {
					continue;
				}
				if (A[nz][nx][ny] == -1) { // 토마토가 없는 칸
					continue;
				}
				if (dist[nz][nx][ny] != -1) {
					continue;
				}

				que.add(nz);
				que.add(nx);
				que.add(ny);
				dist[nz][nx][ny] = dist[z][x][y] + 1;
			}
		}
	}

	static void pro() {
		// TODO: BFS 로 거리 계산
		bfs();

		// TODO: dist 배열을 순회하며 최소 값 계산
		// 처음 모두 익어 있는 상태이면 0
		// 모두 익지 못하는 상황이면 -1
		int answer = 0;

		for (int h = 1; h <= H; h++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (A[h][i][j] == -1) {
						continue;
					}
					if (dist[h][i][j] == -1) {
						System.out.println(-1);
						return;
					}
					answer = Math.max(answer, dist[h][i][j]);
				}
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
