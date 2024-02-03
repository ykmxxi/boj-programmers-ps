import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] A;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		A = new int[M + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static boolean determination(int height) {
		int last = 0;
		for (int i = 1; i <= M; i++) {
			if (A[i] - last <= height) {
				last = A[i] + height;
			} else {
				return false;
			}
		}
		return last >= N;
	}

	static void pro() {
		Arrays.sort(A, 1, M + 1);
		int L = 1, R = N, answer = N;

		while (L <= R) {
			int mid = (L + R) / 2;
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
