import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MOD = 1000000009;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
	}

	static void preprocess() {
		Dy = new int[100005][4];

		Dy[1][1] = 1;
		Dy[2][2] = 1;
		Dy[3][1] = 1;
		Dy[3][2] = 1;
		Dy[3][3] = 1;

		for (int i = 4; i <= 100000; i++) {
			Dy[i][1] = (Dy[i - 1][2] + Dy[i - 1][3]) % MOD;
			Dy[i][2] = (Dy[i - 2][1] + Dy[i - 2][3]) % MOD;
			Dy[i][3] = (Dy[i - 3][1] + Dy[i - 3][2]) % MOD;
		}
	}

	static void pro() {
		int answer = 0;

		for (int i = 1; i <= 3; i++) {
			answer += Dy[N][i];
			answer %= MOD;
		}
		sb.append(answer).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		preprocess();

		while (T-- > 0) {
			input();
			pro();
		}

		System.out.println(sb);
	}
}
