import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, L;
	static int[] visit; // 경사로가 놓인 곳
	static int[][] A;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static boolean check(int x, int y, int dir) {
		int[] height = new int[N];
		visit = new int[N];

		for (int i = 0; i < N; i++) {
			if (dir == 0) { // 행
				height[i] = A[x][i];
			} else {
				height[i] = A[i][y];
			}
		}

		for (int i = 0; i < N - 1; i++) {
			if (height[i] == height[i + 1]) {
				continue;
			}

			if (Math.abs(height[i] - height[i + 1]) != 1) { // 다음 칸과의 차이가 1이 아니면 X
				return false;
			}

			if (height[i] == height[i + 1] - 1) { // 올라가는 경사로
				for (int j = i; j > i - L; j--) {
					if (j < 0 || visit[j] == 1 || height[i] != height[j]) {
						return false;
					}
					visit[j] = 1;
				}
			} else { // 내려가는 경사로
				for (int j = i + 1; j <= i + L; j++) {
					if (j >= N || visit[j] == 1 || height[i + 1] != height[j]) {
						return false;
					}
					visit[j] = 1;
				}
			}
		}

		return true;
	}


	static void pro() {
		int answer = 0;

		for (int i = 0; i < N; i++) {
			if (check(i, 0, 0)) { // 행 탐색
				answer++;
			}
			if (check(0, i, 1)) { // 열 탐색
				answer++;
			}
		}

		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
