import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int K, N;
	static int[] A;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		A = new int[K + 1];

		for (int i = 1; i <= K; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
	}

	static boolean determination(long length) {
		int count = 0;
		for (int i = 1; i <= K; i++) {
			count += A[i] / length;
		}
		return count >= N;
	}

	static void pro() {
		long L = 1;
		long R = Integer.MAX_VALUE;
		long answer = 0;

		while (L <= R) {
			long mid = (L + R) / 2;
			if (determination(mid)) {
				answer = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
