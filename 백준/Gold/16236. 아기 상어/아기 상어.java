import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int INF = Integer.MAX_VALUE;
	static int N, r, c, curSize, curEat, minX, minY, minDist;
	static int[][] A, dist, dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	static class Info {
		int x;
		int y;

		public Info(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());

				if (A[i][j] == 9) {
					r = i;
					c = j;
					A[i][j] = 0;
				}
			}
		}
	}

	static void bfs(int x, int y) {
		Queue<Info> q = new LinkedList<>();

		q.add(new Info(x, y));

		while (!q.isEmpty()) {
			Info info = q.poll();
			for (int k = 0; k < 4; k++) {
				int nx = info.x + dir[k][0];
				int ny = info.y + dir[k][1];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N && A[nx][ny] <= curSize && dist[nx][ny] == 0) {
					dist[nx][ny] = dist[info.x][info.y] + 1;

					if (A[nx][ny] > 0 && A[nx][ny] < curSize) { // 먹을 수 있는 물고기라면
						if (minDist > dist[nx][ny]) { // 현재 저장된 최소거리 보다 짧으면
							minDist = dist[nx][ny]; // 최소 거리를 갱신
							minX = nx; // 먹을 수 있는 물고기 중 현재 최소 거리를 갖는 정보 저장
							minY = ny;
						} else if (minDist == dist[nx][ny]) { // 현재 저장된 최소거리와 같으면
							if (minX == nx) { // 먼저 행을 비교해 행이 같으면
								if (minY > ny) { // 열이 더 작은지 확인해 열이 더 작으면
									minX = nx; // 정보 갱신
									minY = ny;
								}
							} else if (minX > nx) { // 행이 더 짧으면
								minX = nx; // 정보 갱신
								minY = ny;
							}
						}
					}

					q.add(new Info(nx, ny));
				}
			}
		}

	}

	static void pro() {
		int answer = 0;
		curSize = 2;
		curEat = 0;

		while (true) {
			dist = new int[N][N];
			minX = INF;
			minY = INF;
			minDist = INF;

			bfs(r, c);

			if (minX != INF && minY != INF) {
				curEat++;
				A[minX][minY] = 0;
				r = minX;
				c = minY;
				answer += dist[minX][minY];

				if (curEat == curSize) {
					curSize++;
					curEat = 0;
				}
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
