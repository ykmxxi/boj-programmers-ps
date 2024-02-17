import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int n, MOD = 1000000009;
	static int[] Dy;

	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
	}

	static void preprocess() {
		Dy = new int[100005];
		Dy[0] = 1;
		Dy[1] = 1;
		Dy[2] = 2;
		Dy[3] = 2;

		for (int i = 4; i <= 100000; i++) {
			Dy[i] = Dy[i - 2];
			Dy[i] += Dy[i - 4]; // i = 4 부터 시작하기 때문에 i - 4 >= 0 은 always true
			Dy[i] %= MOD;
			if (i >= 6) {
				Dy[i] += Dy[i - 6];
				Dy[i] %= MOD;
			}
		}
	}

	static void pro() {
		sb.append(Dy[n]).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		preprocess();

		while (T > 0) {
			T--;
			input();
			pro();
		}
		System.out.println(sb);
	}
}
