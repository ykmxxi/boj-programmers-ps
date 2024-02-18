/*
	계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임
	각 계단에는 일정한 점수가 쓰여 있고, 밟으면 그 점수를 얻게 됨
	계단을 오르는 규칙
		- 한 번에 한 계단 or 두 게단. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있음
		- 연속된 세 개의 계단을 모두 밟아서는 안됨 (시작점은 미포함)
		- 마지막 도착 계단은 반드시 밟아야 됨
	각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최대값
	정답의 최대치: 계단이 300개 이고, 모두 10,000일 때 모두 밟아도 3,000,000 (3백만, int)
 */

import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N; // 계단 개수, 300 이하의 자연수
	static int[] A;
	static int[][] Dy; // [i][0]: i번째 계단을 밟았을 때 i - 1 계단을 밟지 않은 경우, [i][1]: i번째 계단을 밟았을 때, i - 1 계단을 밟은 경우

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
	}

	static void pro() {
		// 가짜문제를 정의해보자
		// 진짜 문제: 마지막 계단을 밟았는데. 상황은 두 가지: 마지막 전을 밟았을 때, 마지막 전을 밟지 않았을 때
		// 가짜 문제: i번째 계단을 밟았는데, i - 1을 밟았을 때와, 밟지 않았을 때

		Dy = new int[N + 1][2];
		Dy[1][0] = A[1];
		Dy[1][1] = A[1];

		for (int i = 2; i <= N; i++) {
			// 이전 계단을 밟지 않은 경우
			Dy[i][0] = Math.max(Dy[i - 2][0], Dy[i - 2][1]) + A[i];
			// 이전 계단을 밟은 경우는 무조건 전전 계단을 밟지 않아야 함
			Dy[i][1] = Dy[i - 1][0] + A[i];
		}

		int answer = Math.max(Dy[N][0], Dy[N][1]);
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
