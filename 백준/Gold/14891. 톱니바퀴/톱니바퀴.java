import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int K;
	static int[][] A, order;

	static void input() throws IOException {
		A = new int[5][8];
		for (int i = 1; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				A[i][j] = s.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(br.readLine());
		order = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 2; j++) {
				order[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void rotate(int num, int dir) {
		if (dir == 1) { // 시계방향
			int tmp = A[num][7];
			for (int i = 7; i > 0; i--) {
				A[num][i] = A[num][i - 1];
			}
			A[num][0] = tmp;
		} else {
			int tmp = A[num][0];
			for (int i = 0; i < 7; i++) {
				A[num][i] = A[num][i + 1];
			}
			A[num][7] = tmp;
		}
	}

	static void order(int num, int dir) {
		checkLeft(num - 1, -dir); // 왼쪽 확인
		checkRight(num + 1, -dir); // 오른쪽 확인
		rotate(num, dir); // 자기자신 회전
	}

	static void checkLeft(int num, int dir) {
		if (num < 1) {
			return;
		}
		if (A[num][2] == A[num + 1][6]) {
			return;
		}
		checkLeft(num - 1, -dir);
		rotate(num, dir);
	}

	static void checkRight(int num, int dir) {
		if (num > 4) {
			return;
		}
		if (A[num][6] == A[num - 1][2]) {
			return;
		}
		checkRight(num + 1, -dir);
		rotate(num, dir);
	}

	static int getScore() {
		int sum = 0;
		for (int i = 1; i < 5; i++) {
			sum += Math.pow(2, i - 1) * A[i][0];

		}
		return sum;
	}

	static void pro() {
		// 각 명령대로 수행
		for (int[] ord : order) {
			order(ord[0], ord[1]);
		}

		int answer = getScore();
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
