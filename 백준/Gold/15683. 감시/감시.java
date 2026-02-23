import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, ans;
	static int[] selected;
	static int[][] A, B;
	static ArrayList<Cctv> cctvList;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌

	static class Cctv {
		int type;
		int x;
		int y;

		public Cctv(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = N * M;

		A = new int[N][M];
		cctvList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if (A[i][j] > 0 && A[i][j] < 6) {
					cctvList.add(new Cctv(A[i][j], i, j));
				}
			}
		}
	}

	static void bfs(Cctv c, int d) { // watch
		Queue<Cctv> q = new LinkedList<>();

		q.add(c);

		while (!q.isEmpty()) {
			Cctv cctv = q.poll();
			int nx = cctv.x + dir[d][0];
			int ny = cctv.y + dir[d][1];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M || B[nx][ny] == 6) {
				break;
			}

			if (B[nx][ny] == 0) { // 빈 칸이면
				B[nx][ny] = -1;
			}
			q.add(new Cctv(c.type, nx, ny));
		}
	}

	static void watch(Cctv c, int d) {
		int t = c.type;

		if (t == 1) {
			bfs(c, d); // 선택된 방향 감시
		} else if (t == 2) {
			if (d == 0 || d == 2) { // 상하
				bfs(c, 0);
				bfs(c, 2);
			} else { // 좌우
				bfs(c, 1);
				bfs(c, 3);
			}
		} else if (t == 3) {
			if (d == 0) { // 상우
				bfs(c, 0);
				bfs(c, 1);
			} else if (d == 1) { // 우하
				bfs(c, 1);
				bfs(c, 2);
			} else if (d == 2) { // 하좌
				bfs(c, 2);
				bfs(c, 3);
			} else { // 상좌
				bfs(c, 0);
				bfs(c, 3);
			}
		} else if (t == 4) {
			if (d == 0) {
				bfs(c, 0); // 좌상우
				bfs(c, 1);
				bfs(c, 3);
			} else if (d == 1) {
				bfs(c, 0); // 상우하
				bfs(c, 1);
				bfs(c, 2);
			} else if (d == 2) {
				bfs(c, 1); // 좌하우
				bfs(c, 2);
				bfs(c, 3);
			} else {
				bfs(c, 0); // 상좌하
				bfs(c, 2);
				bfs(c, 3);

			}
		} else { // 상하좌우
			bfs(c, 0);
			bfs(c, 1);
			bfs(c, 2);
			bfs(c, 3);
		}
	}

	static int calculate() {
		int cnt = 0;
		for (int[] row : B) {
			for (int value : row) {
				if (value == 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	static void rec_func(int k, int cctvCnt) {
		if (k == cctvCnt) { // 모든 cctv 감시 방향이 정해지면
			B = new int[N][M];
			for (int i = 0; i < N; i++) {
				B[i] = A[i].clone();
			}

			for (int i = 0; i < cctvList.size(); i++) {
				watch(cctvList.get(i), selected[i]);
			}
			ans = Math.min(ans, calculate());
		} else {
			for (int i = 0; i < 4; i++) {
				selected[k] = i;
				rec_func(k + 1, cctvCnt);
				selected[k] = 0;
			}
		}
	}

	static void pro() {
		// CCTV
		int cnt = cctvList.size();
		selected = new int[cnt];

		rec_func(0, cnt);

		System.out.println(ans);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
