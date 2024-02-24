import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] A;
	static long[][] Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		A = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		Dy = new long[N + 1][21];

		Dy[1][A[1]] = 1;

		for (int i = 2; i <= N - 1; i++) {
			for (int prev = 0; prev <= 20; prev++) {
				for (int current : new int[]{prev - A[i], prev + A[i]}) {
					if (current < 0 || current > 20) {
						continue;
					}
					Dy[i][current] += Dy[i - 1][prev];
				}
			}
		}
		System.out.println(Dy[N - 1][A[N]]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
