import java.util.*;
import java.io.*;

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

	static boolean isGood(int target_idx) {
		int L = 1, R = N;
		int target = A[target_idx];

		while (L < R) {
			if (L == target_idx) {
				L++;
			} else if (R == target_idx) {
				R--;
			} else {
				if (A[L] + A[R] == target) {
					return true;
				}
				if (A[L] + A[R] > target) {
					R--;
				}
				if (A[L] + A[R] < target) {
					L++;
				}
			}
		}
		return false;
	}

	static void pro() {
		Arrays.sort(A, 1, N + 1);

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (isGood(i)) {
				answer++;
			}
		}

		sb.append(answer);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
