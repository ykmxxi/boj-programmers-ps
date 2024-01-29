import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static int[] A;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static boolean determination(int H) {
		// H 높이로 잘랐을 때, M 만큼 얻을 수 있으면 true, 불가능하면 false
		long sum = 0L;

		for (int length : A) {
			if (length > H) {
				sum += length - H;
			}
		}

		return sum >= M;
	}

	static void pro() {
		long answer = 0L;

		long L = 1L;
		long R = 2000000000L;

		while (L <= R) {
			long mid = (L + R) / 2;

			if (determination((int)mid)) { // 떡을 mid 길이로 잘랐을 때 남은 길이의 총 합이 M보다 크면 길이를 더 늘려도 됨, L을 땡겨서 H를 늘리자
				L = mid + 1;
				answer = mid;
			} else { // 떡을 mid 길이로 잘랐을 때 남은 길이의 총 합이 M보다 작으면 길이를 줄여서 총 합을 늘려야 됨, R을 땡겨서 H를 줄이자
				R = mid - 1;
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
