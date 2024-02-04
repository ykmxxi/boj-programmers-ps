import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, k;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
	}

	static boolean determination(long candidate) {
		long sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += Math.min(N, candidate / i);
		}
		return sum >= k;
	}

	static void pro() {
		long L = 1, R = (long)N * N, answer = 0;

		while (L <= R) {
			long mid = (L + R) / 2;
			if (determination(mid)) {
				answer = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
