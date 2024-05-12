import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M, R, score;
	static int[][] A;
	static char[][] state;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N + 1][M + 1];
		state = new char[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				state[i][j] = 'S';
			}
		}
	}

	static void pro(String attack, String defense) {
		int length = 0;
		String[] attackInfo = attack.split(" ");
		String[] defenseInfo = defense.split(" ");

		// 공격 진행
		int aX = Integer.parseInt(attackInfo[0]);
		int aY = Integer.parseInt(attackInfo[1]);

		if (attackInfo[2].equals("E")) {
			length = A[aX][aY] + aY;
			for (int y = aY; y <= M; y++) {
				if (state[aX][y] == 'F') {
					continue;
				}

				if (y < length) {
					state[aX][y] = 'F';
					score++;
				} else {
					break;
				}
				length = Math.max(length, A[aX][y] + y);
			}
		}
		if (attackInfo[2].equals("W")) {
			length = aY - A[aX][aY];
			for (int y = aY; y >= 1; y--) {
				if (state[aX][y] == 'F') {
					continue;
				}

				if (y > length) {
					state[aX][y] = 'F';
					score++;
				} else {
					break;
				}
				length = Math.min(length, y - A[aX][y]);
			}
		}
		if (attackInfo[2].equals("S")) {
			length = A[aX][aY] + aX;
			for (int x = aX; x <= N; x++) {
				if (state[x][aY] == 'F') {
					continue;
				}

				if (x < length) {
					state[x][aY] = 'F';
					score++;
				} else {
					break;
				}
				length = Math.max(length, A[x][aY] + x);
			}

		}
		if (attackInfo[2].equals("N")) {
			length = aX - A[aX][aY];
			for (int x = aX; x >= 1; x--) {
				if (state[x][aY] == 'F') {
					continue;
				}

				if (x > length) {
					state[x][aY] = 'F';
					score++;
				} else {
					break;
				}
				length = Math.min(length, x - A[x][aY]);
			}
		}

		// 수비 진행
		int dX = Integer.parseInt(defenseInfo[0]);
		int dY = Integer.parseInt(defenseInfo[1]);
		state[dX][dY] = 'S';
	}

	public static void main(String[] args) throws IOException {
		input();

		while (R-- > 0) {
			String attack = br.readLine();
			String defense = br.readLine();

			pro(attack, defense);
		}

		sb.append(score).append('\n');
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(state[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}
