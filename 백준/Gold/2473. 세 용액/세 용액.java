import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] A;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		Arrays.sort(A, 1, N + 1);

		long answer = Long.MAX_VALUE;
		int v1 = 0, v2 = 0, v3 = 0; // 세 가지 용액
		for (int i = 1; i <= N - 2; i++) {
			int L = i + 1;
			int R = N;
			int target = -A[i];

			while (L < R) {
				if (Math.abs(-(long)target + A[L] + A[R]) < answer) {
					answer = Math.abs(-(long)target + A[L] + A[R]);
					v1 = -target;
					v2 = A[L];
					v3 = A[R];
				}

				if (A[L] + A[R] > target) { // A[i] 는 기준값. 최소 A[L] + 최대 A[R] > 0 -> 오른쪽 구간 줄이기
					R--;
				} else {
					L++;
				}
			}
		}

		sb.append(v1).append(' ').append(v2).append(' ').append(v3);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
