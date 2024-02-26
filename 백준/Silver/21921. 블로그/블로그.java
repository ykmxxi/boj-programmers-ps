/*
	N: 블로그를 시작한 후 현재까지의 날
	X일 동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지 구하는 문제

	정답의 최대치: 2 * 10^5 * 10^3 = 2 * 10^8 (int)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, X;
	static int[] A; // 1일차 부터 N일차 까지 순서대로

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		int answer = 0;
		int cnt = 1;

		int sum = 0;
		int L = 1;
		int R = X;

		for (int i = 1; i <= X; i++) {
			sum += A[i];
		}

		answer = sum;
		while (true) {
			L++;
			R++;

			if (R > N) {
				break;
			}

			sum = sum + A[R] - A[L - 1];
			if (answer == sum) {
				cnt++;
			} else if (answer < sum) {
				answer = sum;
				cnt = 1;
			}
		}

		if (answer == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(answer);
			System.out.println(cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
