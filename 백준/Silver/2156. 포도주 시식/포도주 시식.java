import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N;
	static int[] A, Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		A = new int[N + 5];
		Dy = new int[N + 5];

		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
	}

	static void pro() {
		Dy[1] = A[1];
		Dy[2] = A[1] + A[2];
		Dy[3] = Math.max(Dy[2], Math.max(A[1] + A[3], A[2] + A[3]));

		for (int i = 4; i <= N; i++) {
			// Math.max(OOX 인 경우, Math.max(OXO 인 경우, XOO 인 경우))
			Dy[i] = Math.max(Dy[i - 1], Math.max(Dy[i - 2] + A[i], Dy[i - 3] + A[i - 1] + A[i]));
		}

		System.out.println(Dy[N]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}