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
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
	}

	static void pro() {
		// 정렬
		Arrays.sort(A, 1, N + 1);
		int answer = Integer.MAX_VALUE;
		int R = 1;

		for (int L = 1; L <= N; L++) {
			while (R + 1 <= N && A[R] - A[L] < M) {
				R++;
			}
			if (A[R] - A[L] >= M) {
				answer = Math.min(answer, A[R] - A[L]);
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
