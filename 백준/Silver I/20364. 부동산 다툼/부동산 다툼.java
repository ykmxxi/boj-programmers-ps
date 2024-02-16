import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, Q;
	static boolean[] estate; // 땅의 점유 여부 저장 배열

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		estate = new boolean[N + 1];
	}

	static void pro() throws IOException {
		while (Q-- > 0) {
			int answer = 0;
			int x = Integer.parseInt(br.readLine());
			int y = x;

			while (x > 0) {
				if (estate[x]) {
					answer = x;
				}
				x /= 2;
			}

			estate[y] = true;
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
