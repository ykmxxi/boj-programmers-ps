import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int[][] Dy;

	static void pro() {
		int answer = 0;
		Dy = new int[N + 1][11];

		// 초기값 설정
		for (int i = 0; i <= 9; i++) {
			Dy[1][i] = 1;
		}

		for (int length = 2; length <= N; length++) {
			for (int last = 0; last <= 9; last++) {
				for (int prev = 0; prev <= last; prev++) {
					Dy[length][last] += Dy[length - 1][prev];
					Dy[length][last] %= 10007;
				}
			}
		}

		for (int last = 0; last <= 9; last++) {
			answer += Dy[N][last];
			answer %= 10007;
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		pro();
	}
}
