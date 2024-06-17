import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, H, ans = -1;
	static boolean flag;
	static int[][] A;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		A = new int[H + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			A[a][b] = 1;
		}
	}

	static boolean check() {
		for (int i = 1; i <= N; i++) { // 1번 부터 N번 세로선에 대해
			int curCol = i;
			int level = 1;

			while (level <= H) {
				if (A[level][curCol] == 1) {
					curCol++;
					level++;
				} else if (A[level][curCol - 1] == 1) {
					curCol--;
					level++;
				} else {
					level++;
				}
			}

			if (i != curCol) {
				return false;
			}
		}

		return true;
	}

	static void rec_func(int curRow, int cnt, int size) {
		if (cnt == size) {
			if (check()) {
				ans = size;
				flag = true;
			}
			return;
		}
		for (int row = curRow; row <= H; row++) {
			for (int col = 1; col < N; col++) {
				if (A[row][col] == 1) { // 이미 사다리가 있는 경우
					continue;
				}
				if (A[row][col - 1] == 1) { // 왼쪽과 연결된 사다리가 있는 경우
					continue;
				}
				if (A[row][col + 1] == 1) { // 오른쪽과 연결된 사다리가 있는 경우
					continue;
				}

				A[row][col] = 1;
				rec_func(row, cnt + 1, size);
				A[row][col] = 0;
			}

		}
	}

	static void pro() {
		for (int i = 0; i <= 3; i++) {
			if (!flag) {
				rec_func(1, 0, i);
			}
		}

		System.out.println(ans);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
