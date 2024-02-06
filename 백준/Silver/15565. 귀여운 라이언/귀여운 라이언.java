import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, K;
	static int[] A;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		int R = 0;
		int answer = -1;
		int cnt = 0;

		for (int L = 1; L <= N; L++) {
			while (R < N && cnt < K) {
				R++;
				if (A[R] == 1) {
					cnt++;
				}
			}

			if (cnt == K) {
				if (answer == -1) {
					answer = R - L + 1;
				}
				answer = Math.min(answer, R - L + 1);
			}

			if (A[L] == 1) {
				cnt--;
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
