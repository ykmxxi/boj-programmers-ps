import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] Dy, price;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		Dy = new int[N + 1];
		price = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		// 초기화
		Dy[0] = 0;
		Dy[1] = price[1];

		for (int i = 2; i <= N; i++) {
			Dy[i] = i * Dy[1];
			for (int j = 2; j <= i; j++) {
				Dy[i] = Math.max(Dy[i], Dy[i - j] + price[j]);
			}
		}

		System.out.println(Dy[N]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
