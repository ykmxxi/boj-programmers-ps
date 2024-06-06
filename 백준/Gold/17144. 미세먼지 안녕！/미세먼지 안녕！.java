import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int R, C, T, ux, dx;
	static int[][] A, B, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		A = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if (A[i][j] == -1) {
					if (ux == 0) {
						ux = i;
					} else {
						dx = i;
					}
				}
			}
		}
	}

	static void spread(int x, int y) {
		int amount = A[x][y] / 5;
		int cnt = 0;

		for (int k = 0; k < 4; k++) {
			int nx = x + dir[k][0];
			int ny = y + dir[k][1];

			if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 범위 내에서
				if (A[nx][ny] != -1) { // 공기청정기가 없으면
					// 확산
					B[nx][ny] += amount;
					cnt++;
				}
			}
		}
		B[x][y] -= cnt * amount;
	}

	static void sum() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				A[i][j] += B[i][j];
			}
		}
	}

	static void operate() {
		int top = ux; // 공기청정기 윗 부분좌표며,  반시계 방향으로 진행

		for (int x = top - 1; x > 0; x--) {
			A[x][0] = A[x - 1][0];
		}

		for (int y = 0; y < C - 1; y++) {
			A[0][y] = A[0][y + 1];
		}

		for (int x = 0; x < top; x++) {
			A[x][C - 1] = A[x + 1][C - 1];
		}

		for (int y = C - 1; y > 1; y--) {
			A[top][y] = A[top][y - 1];
		}

		A[top][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.

		int bottom = dx; // 공기청정기 밑 부분좌표며, 시계방향으로 진행

		for (int x = bottom + 1; x < R - 1; x++) {
			A[x][0] = A[x + 1][0];
		}

		for (int y = 0; y < C - 1; y++) {
			A[R - 1][y] = A[R - 1][y + 1];
		}

		for (int x = R - 1; x > bottom; x--) {
			A[x][C - 1] = A[x - 1][C - 1];
		}

		for (int y = C - 1; y > 1; y--) {
			A[bottom][y] = A[bottom][y - 1];
		}

		A[bottom][1] = 0; // 공기청정기로 나가는 곳이므로 먼지는 0이다.

	}

	static int calculate() {
		int sum = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (A[i][j] == -1) {
					continue;
				}
				sum += A[i][j];
			}
		}
		return sum;
	}

	static void pro() {
		while (T-- > 0) {
			B = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 미세먼지 확산
					if (A[i][j] > 4) {
						spread(i, j);
					}
				}
			}

			// 미세먼지 +, -를 저장한 B와 A를 합치기
			sum();

			// 공기청정기 작동
			operate();
		}

		// 남아있는 미세먼지 계산
		System.out.println(calculate());
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
