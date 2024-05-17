import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static long[][] dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		dy = new long[N + 1][2];
	}

	static void pro() {
		// 초기값 설정
		long answer = 0L;
		dy[1][0] = 1L;
		dy[1][1] = 1L;

		for (int i = 2; i <= N; i++) {
			if (i % 2 == 0) {
				dy[i][0] = dy[i - 1][0];
				dy[i][1] = dy[i - 1][0] + dy[i - 1][1];
			} else {
				dy[i][0] = dy[i - 1][0] + dy[i - 1][1];
				dy[i][1] = dy[i - 1][1];
			}
		}

		answer = 2 * (dy[N][0] + dy[N][1]);
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
