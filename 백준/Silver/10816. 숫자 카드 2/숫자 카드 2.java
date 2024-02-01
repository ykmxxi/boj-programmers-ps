import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[] A, B;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		B = new int[M + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
	}

	static int lower_bound(int[] A, int L, int R, int X) {
		int leftIdx = R + 1;
		while (L <= R) {
			int mid = (L + R) / 2;

			if (A[mid] >= X) {
				leftIdx = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return leftIdx;
	}

	static int upper_bound(int[] A, int L, int R, int X) {
		int rightIdx = R + 1;
		while (L <= R) {
			int mid = (L + R) / 2;

			if (A[mid] > X) {
				rightIdx = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return rightIdx;
	}

	static void pro() {
		// 이분 탐색할 배열을 정렬
		Arrays.sort(A, 1, N + 1);

		// 이분 탐색 시작
		for (int i = 1; i <= M; i++) {
			int answer = upper_bound(A, 1, N, B[i]) - lower_bound(A, 1, N, B[i]);
			sb.append(answer).append(' ');
		}

		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
