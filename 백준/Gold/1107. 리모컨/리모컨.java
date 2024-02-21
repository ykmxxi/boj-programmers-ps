import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static boolean[] A; // 고장난 버튼을 저장

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		A = new boolean[12]; // 0 ~ 9, +, -
		if (M != 0) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < M; i++) {
				String s = st.nextToken();
				if (s.equals("+")) {
					A[10] = true;
				} else if (s.equals("-")) {
					A[11] = true;
				} else {
					A[Integer.parseInt(s)] = true;
				}
			}
		}
	}

	static boolean canMove(int num) {
		do {
			if (A[num % 10]) {
				return false;
			}
			num = num / 10;
		} while (num > 0);

		return true;
	}

	static int getLength(int num) {
		int len = 0;

		do {
			len++;
			num /= 10;
		} while (num > 0);

		return len;
	}

	static int changeAndMove() {
		int result = 500000;

		for (int i = 0; i <= 999999; i++) {
			if (canMove(i)) { // 해당 채널을 누를 수 있으면
				int dist = getLength(i) + Math.abs(i - N);

				result = Math.min(result, dist);
			}
		}

		return result;
	}

	static void pro() {
		int answer = 0;

		// 100 에서 + or - 를 반복
		answer = Math.abs(N - 100);

		// N과 가장 가까운 번호로 이동해 + or - 로 이동
		answer = Math.min(answer, changeAndMove());

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
