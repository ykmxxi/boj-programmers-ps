import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] Dy;

	static void preprocess() {
		Dy = new int[12];

		Dy[1] = 1;
		Dy[2] = 2;
		Dy[3] = 4;

		for (int i = 4; i <= 11; i++) {
			Dy[i] = Dy[i - 1] + Dy[i - 2] + Dy[i - 3];
		}
	}

	static void pro() throws IOException {
		int T = Integer.parseInt(br.readLine());

		while (T > 0) {
			T--;
			n = Integer.parseInt(br.readLine());
			sb.append(Dy[n]).append('\n');
		}

		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		preprocess();
		pro();
	}
}
