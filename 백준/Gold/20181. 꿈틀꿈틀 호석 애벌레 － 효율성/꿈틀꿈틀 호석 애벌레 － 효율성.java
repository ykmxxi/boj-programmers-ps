import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, K;
	static long[] A, Dy;
	static ArrayList<Interval>[] intervals;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new long[N + 1];
		Dy = new long[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		intervals = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			intervals[i] = new ArrayList<>();
		}

	}

	static void pro() {
		long sum = 0;

		for (int L = 1, R = 0; L <= N; L++) {
			while (sum < K && R + 1 <= N) {
				R++;
				sum += A[R];

				// sum += A[++R];
			}

			if (sum >= K) {
				Interval i = new Interval();
				i.left = L;
				i.satisfy = sum - K;
				intervals[R].add(i); // 구간의 정보를 저장
			}

			sum -= A[L]; // L이 이동하면 만족도를 빼줘야 함
		}

		for (int R = 1; R <= N; R++) {
			Dy[R] = Dy[R - 1]; // 안 먹은 경우

			for (Interval i : intervals[R]) {
				Dy[R] = Math.max(Dy[R], Dy[i.left - 1] + i.satisfy);
			}
		}

		System.out.println(Dy[N]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

	static class Interval {
		int left;
		long satisfy;
	}
}
