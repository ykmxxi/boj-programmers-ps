import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N1, M1, N2, M2;
	static char[][] A, B, R;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N1 = Integer.parseInt(st.nextToken());
		M1 = Integer.parseInt(st.nextToken());

		A = new char[101][101];
		for (int i = 1; i <= N1; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M1; j++) {
				A[i][j] = s.charAt(j - 1);
			}

		}

		st = new StringTokenizer(br.readLine(), " ");
		N2 = Integer.parseInt(st.nextToken());
		M2 = Integer.parseInt(st.nextToken());

		B = new char[101][101];
		for (int i = 1; i <= N2; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M2; j++) {
				B[i][j] = s.charAt(j - 1);
			}
		}
	}

	static void rotate() { // 퍼즐 회전
		R = new char[101][101];
		for (int i = 1; i <= N2; i++) {
			for (int j = 1; j <= M2; j++) {
				R[j][N2 - i + 1] = B[i][j];
			}
		}

		// 회전 시 행과 열이 바뀜
		int temp = N2;
		N2 = M2;
		M2 = temp;

		// 회전 시킨 배열을 원래 배열에 저장
		for (int i = 1; i <= N2; i++) {
			for (int j = 1; j <= M2; j++) {
				B[i][j] = R[i][j];
			}
		}
	}

	static boolean possible(int shift_row, int shift_col) { // 평행 이동한 결과에서 겹치는 것이 존재하는지 확인하는 함수
		for (int ai = 1; ai <= N1; ai++) {
			for (int aj = 1; aj <= M1; aj++) {
				if (A[ai][aj] == '0') {
					continue;
				}

				int bi = ai + shift_row;
				int bj = aj + shift_col;

				if (1 <= bi && bi <= N2 && 1 <= bj && bj <= M2 && B[bi][bj] == '1') {
					return false;
				}
			}
		}

		return true;
	}

	static void pro() {
		int answer = Integer.MAX_VALUE;
		for (int r = 1; r <= 4; r++) {
			rotate();

			for (int shift_row = -51; shift_row <= 51; shift_row++) {
				for (int shift_col = -51; shift_col <= 51; shift_col++) {
					if (possible(shift_row, shift_col)) {
						int row = Math.max(N2 - 1, shift_row + N1 - 1) - Math.min(0, shift_row) + 1;
						int col = Math.max(M2 - 1, shift_col + M1 - 1) - Math.min(0, shift_col) + 1;
						answer = Math.min(answer, row * col);
					}
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
