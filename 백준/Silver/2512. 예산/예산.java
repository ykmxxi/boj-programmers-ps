import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M; // 지방의 수, 총 예산
	static int[] A;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
	}

	static boolean determination(int limit) {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += Math.min(A[i], limit);
		}
		return sum <= M;
	}

	static void pro() {
		int L = 0, R = 0, ans = 0;
		for (int i = 1; i <= N; i++) {
			R = Math.max(R, A[i]);
		}

		while (L <= R) {
			int mid = (L + R) / 2;
			if (determination(mid)) {
				ans = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}

		}
		System.out.println(ans);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
