import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] selected, used;

	static void rec_func(int k) {
		if (k == N) {
			for (int i = 0; i < N; i++) {
				sb.append(selected[i]).append(' ');
			}
			sb.append('\n');
		} else {
			for (int cand = 1; cand <= N; cand++) {
				if (used[cand] == 0) {
					used[cand] = 1;
					selected[k] = cand;

					rec_func(k + 1);

					used[cand] = 0;
					selected[k] = 0;
				}
			}
		}
	}

	static void pro() throws IOException {
		N = Integer.parseInt(br.readLine());

		selected = new int[N];
		used = new int[N + 1];

		rec_func(0);

		System.out.print(sb);
	}

	public static void main(String[] args) throws IOException {
		pro();
	}
}
