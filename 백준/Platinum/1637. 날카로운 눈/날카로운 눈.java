import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] A;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1][3]; // N개의 줄에 걸쳐 A C B를 입력 받음

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int count(int A, int C, int B, int candidate) { // 어떤 수 candidate 까지의 개수 누적합 계산
		// A, A + B, A + 2B, ..., A + kB (A + kB <= C)
		if (candidate < A) {
			return 0;
		}
		if (C < candidate) {
			// A, A + B, ..., A + kB -> k + 1개 -> A + kB <= C, k + 1 <= (C-A) / B + 1
			return (C - A) / B + 1;
		}
		// A, A + B, ..., A + xB(candidate), ..., A + kB -> x + 1 <= (candidate - A) / B + 1
		return (candidate - A) / B + 1;
	}

	static boolean determination(int candidate) { // 어떤 수 candidate 까지의 개수 누적합이 홀수?
		long sum = 0;

		for (int i = 1; i <= N; i++) {
			sum += count(A[i][0], A[i][1], A[i][2], candidate);
		}
		return sum % 2 == 1; // 홀수이면 true, 짝수이면 false
	}

	static void pro() {
		long L = 1, R = Integer.MAX_VALUE, answer = 0, cnt = 0;

		// 이분 탐색
		while (L <= R) {
			long mid = (L + R) / 2;
			if (determination((int)mid)) { // 홀수 개 이면
				answer = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		if (answer == 0) {
			System.out.println("NOTHING");
		} else {
			for (int i = 1; i <= N; i++) {
				// A 보다 answer이 크고, C보다 answer이 작을때 answer이 해당 정수더미에 존재
				// answer = A + kB 이기 때문에, answer - A를 B로 나눈 나머지는 0이 되어야 한다.
				if (A[i][0] <= answer && answer <= A[i][1] && (answer - A[i][0]) % A[i][2] == 0) {
					cnt++;
				}
			}
			System.out.println(answer + " " + cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
