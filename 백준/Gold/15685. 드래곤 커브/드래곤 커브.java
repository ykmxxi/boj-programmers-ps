import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] A, dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; // 우(x증가) 상(y감소) 좌(x감소) 하(y증가)
	static ArrayList<Integer>[] curves;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		A = new int[101][101];
		curves = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			curves[i] = new ArrayList<>();
			while (st.hasMoreTokens()) {
				curves[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}

	static ArrayList<Integer> getDir(int startD, int generation) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(startD);

		while (generation-- > 0) {
			for (int i = list.size() - 1; i >= 0; i--) {
				int direction = list.get(i) + 1;
				if (direction == 4) {
					direction = 0;
				}
				list.add(direction);
			}
		}

		return list;
	}

	static void check(ArrayList<Integer> curve) {
		int x = curve.get(0);
		int y = curve.get(1);
		int d = curve.get(2);
		int g = curve.get(3);

		ArrayList<Integer> dirs = getDir(d, g);

		A[x][y] = 1;
		for (int k : dirs) {
			int nx = x + dir[k][0];
			int ny = y + dir[k][1];

			A[nx][ny] = 1;
			x = nx;
			y = ny;
		}
	}

	static boolean isSquare(int x, int y) {
		// (0, 0)부터 시작하기 때문에 우하향 사각형만 보면됨
		return A[x][y + 1] == 1 && A[x + 1][y] == 1 && A[x + 1][y + 1] == 1;
	}

	static void pro() {
		int answer = 0; // 정답의 최대치: 100 x 100 모두 커브에 포함되어도 10^4개
		// 저장한 드래곤 커브 정보를 A에 표현, 드래곤 커브에 속한 점은 1
		for (ArrayList<Integer> curve : curves) {
			// 표시
			check(curve);
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (A[i][j] == 1 && isSquare(i, j)) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
