import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n, x;
	static int[] A;

	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		A = new int[n + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		x = Integer.parseInt(br.readLine());
	}

	static void pro() {
		Arrays.sort(A, 1, n + 1);
		int answer = 0;
		int L = 1, R = n;
		while (L < R) {
			if (A[L] + A[R] == x) {
				answer++;
			}
			if (A[L] + A[R] >= x) {
				R--;
			} else {
				L++;
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
