import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] A;
	static int[][][] Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1][4];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void pro() {
		Dy = new int[2][N + 1][4];

		for (int i = 1; i <= 3; i++) {
			Dy[0][1][i] = A[1][i];
			Dy[1][1][i] = A[1][i];
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= 3; j++) {
				if (j == 1) {
					Dy[0][i][j] = Math.min(Dy[0][i - 1][j + 1], Dy[0][i - 1][j]) + A[i][j];
					Dy[1][i][j] = Math.max(Dy[1][i - 1][j + 1], Dy[1][i - 1][j]) + A[i][j];
				} else if (j == 2) {
					Dy[0][i][j] =
						Math.min(Dy[0][i - 1][j], Math.min(Dy[0][i - 1][j - 1], Dy[0][i - 1][j + 1])) + A[i][j];
					Dy[1][i][j] =
						Math.max(Dy[1][i - 1][j], Math.max(Dy[1][i - 1][j - 1], Dy[1][i - 1][j + 1])) + A[i][j];
				} else {
					Dy[0][i][j] = Math.min(Dy[0][i - 1][j], Dy[0][i - 1][j - 1]) + A[i][j];
					Dy[1][i][j] = Math.max(Dy[1][i - 1][j], Dy[1][i - 1][j - 1]) + A[i][j];
				}
			}
		}

		int max = -1, min = Integer.MAX_VALUE;
		for (int i = 1; i <= 3; i++) {
			max = Math.max(max, Dy[1][N][i]);
			min = Math.min(min, Dy[0][N][i]);
		}

		sb.append(max).append(' ').append(min);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
