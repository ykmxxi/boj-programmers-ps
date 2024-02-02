import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, S;
	static int[] A;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		int R = 0;
		int sum = 0;
		int answer = N + 1;

		for (int L = 1; L <= N; L++) {
			sum -= A[L - 1];

			while (R + 1 <= N && sum < S) {
				R++;
				sum += A[R];
			}

			if (sum >= S) {
				answer = Math.min(answer, R - L + 1);
			}
		}

		if (answer == N + 1) {
			answer = 0;
		}
		sb.append(answer);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
