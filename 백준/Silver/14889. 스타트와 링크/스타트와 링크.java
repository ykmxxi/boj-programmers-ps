import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, answer = Integer.MAX_VALUE;
	static int[][] A;
	static int[] visit;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		A = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int getStats() {
		int start_stats = 0;
		int link_stats = 0;

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				// i 번째 사람과 j 번째 사람이 1 이면 스타트팀으로 점수 플러스
				if (visit[i] == 1 && visit[j] == 1) {
					start_stats += A[i][j];
					start_stats += A[j][i];
				}
				// i 번째 사람과 j 번째 사람이 0 이면 링크팀으로 점수 플러스
				if (visit[i] == 0 && visit[j] == 0) {
					link_stats += A[i][j];
					link_stats += A[j][i];
				}
			}
		}
		// 두 팀의 점수 차이 (절댓값)
		return Math.abs(start_stats - link_stats);

	}

	static void rec_func(int k, int idx) {
		if (k == N / 2 + 1) {
			int diff = getStats();
			answer = Math.min(answer, diff);
			if (answer == 0) {
				System.out.println(answer);
				System.exit(0);
			}
		} else {
			for (int i = idx; i <= N; i++) {
				visit[i] = 1;
				rec_func(k + 1, i + 1);
				visit[i] = 0;
			}
		}

	}

	static void pro() {
		// 두 팀에 사람을 배정, 각 N / 2 명 씩
		visit = new int[N + 1];

		rec_func(1, 1);

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
