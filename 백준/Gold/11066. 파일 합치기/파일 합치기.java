import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int K;
	static int[] A;
	static int[][] Dy, sum;

	static void input() throws IOException {
		K = Integer.parseInt(br.readLine());

		A = new int[K + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= K; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Dy = new int[K + 1][K + 1];
		sum = new int[K + 1][K + 1];
	}

	static void preprocess() {
		for (int i = 1; i <= K; i++) {
			for (int j = i; j <= K; j++) {
				sum[i][j] = sum[i][j - 1] + A[j];
			}
		}
	}

	static void pro() {
		preprocess();

		// 구간의 길이가 1이면 0이기 때문에 구간의 길이가 2일때 부터 시작
		for (int len = 2; len <= K; len++) {
			for (int i = 1; i <= K - len + 1; i++) {
				int j = i + len - 1;
				Dy[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					Dy[i][j] = Math.min(Dy[i][j], Dy[i][k] + Dy[k + 1][j] + sum[i][j]);
				}
			}
		}

		sb.append(Dy[1][K]).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while (T > 0) {
			T--;
			input();
			pro();
		}
		System.out.println(sb);
	}
}
