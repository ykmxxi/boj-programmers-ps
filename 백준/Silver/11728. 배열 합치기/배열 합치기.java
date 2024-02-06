import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] A, B;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N + 1];
		B = new int[M + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		int pA = 1;
		int pB = 1;

		while (pA <= N && pB <= M) {
			if (A[pA] <= B[pB]) {
				sb.append(A[pA]).append(' ');
				pA++;
			} else {
				sb.append(B[pB]).append(' ');
				pB++;
			}
		}

		while (pA <= N) {
			sb.append(A[pA]).append(' ');
			pA++;
		}

		while (pB <= M) {
			sb.append(B[pB]).append(' ');
			pB++;
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
