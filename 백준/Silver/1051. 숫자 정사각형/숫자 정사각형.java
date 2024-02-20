import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static String[] A;
	static int[][] dir = {{1, 0}, {0, 1}, {1, 1}};

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new String[N];
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine();
		}
	}

	static int getArea(int x, int y) {
		int len = 0;
		char ch = A[x].charAt(y);

		for (int i = 1; i < Math.min(N, M); i++) {
			int cnt = 0;
			for (int k = 0; k < 3; k++) {
				int nx = x + dir[k][0] * i;
				int ny = y + dir[k][1] * i;

				if (nx >= N || ny >= M) {
					continue;
				}

				if (ch == A[nx].charAt(ny)) {
					cnt++;
				}

				if (cnt == 3) {
					len = i + 1;
				}
			}
		}

		return len * len;
	}

	static void pro() {
		int answer = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int area = getArea(i, j);
				answer = Math.max(answer, area);
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
