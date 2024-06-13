import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] T, P, C, Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		T = new int[N + 1];
		P = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		Dy = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (i + T[i] - 1 <= N) {
				Dy[i + T[i] - 1] = Math.max(Dy[i + T[i] - 1], Dy[i - 1] + P[i]);
			}

			Dy[i] = Math.max(Dy[i], Dy[i - 1]);
		}
		System.out.println(Dy[N]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
