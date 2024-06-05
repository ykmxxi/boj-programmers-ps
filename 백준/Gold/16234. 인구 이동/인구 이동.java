import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, L, R;
	static int[][] A, visit, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayList<Info> tmp;
	static ArrayList<ArrayList<Info>> union;

	static class Info {
		int x;
		int y;

		public Info(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void bfs(int x, int y) {
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(x, y));
		tmp.add(new Info(x, y));
		visit[x][y] = 1;

		while (!q.isEmpty()) {
			Info info = q.poll();

			for (int k = 0; k < 4; k++) {
				int nx = info.x + dir[k][0];
				int ny = info.y + dir[k][1];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N && visit[nx][ny] != 1) {
					int diff = Math.abs(A[info.x][info.y] - A[nx][ny]);

					if (diff >= L && diff <= R) {
						q.add(new Info(nx, ny));
						tmp.add(new Info(nx, ny));
						visit[nx][ny] = 1;
					}
				}
			}
		}
	}

	static boolean canOpen() {
		union = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == 1) { // 이미 연합에 소속되어 있으면
					continue;
				}
				tmp = new ArrayList<>(); // 연합에 속한 국가의 좌표정보 리스트
				bfs(i, j); // 연합 탐색
				if (tmp.size() > 1) {
					union.add(tmp);
				}
			}
		}

		return union.size() > 0;
	}

	static void move() {
		for (ArrayList<Info> u : union) {
			int sum = 0;

			for (Info info : u) {
				sum += A[info.x][info.y];
			}

			int result = sum / u.size();
			for (Info info : u) {
				A[info.x][info.y] = result;
			}
		}
	}

	static void pro() {
		int answer = 0;

		while (true) {
			visit = new int[N][N];
			if (canOpen()) { // 국경이 열려 한 개의 연합이라도 생기면
				// 하루동안 인구 이동
				move();
				answer++;
			} else {
				break;
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
