import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, max, min;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
	}

	static int countOdd(int num) {
		int cnt = 0;

		while (num > 0) {
			if ((num % 10) % 2 == 1) {
				cnt++;
			}
			num /= 10;
		}
		return cnt;
	}

	static void calculate(int num, int total_cnt) {
		// 1. 수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다
		// 수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다
		if (num < 10) {
			min = Math.min(min, total_cnt);
			max = Math.max(max, total_cnt);
			return;
		}
		if (num < 100) { // 수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다
			int newNum = (num / 10) + (num % 10);
			calculate(newNum, total_cnt + countOdd(newNum));
			return;
		}

		// 수가 세 자리 이상이면 3개의 수로 분할, 3개를 더한 값을 새로운 수로 생각한다
		// num1 + num2 + num3 = num의 자리수
		String nStr = Integer.toString(num);
		for (int i = 1; i <= nStr.length() - 2; i++) {
			for (int j = i + 1; j <= nStr.length() - 1; j++) {
				int num1 = Integer.parseInt(nStr.substring(0, i));
				int num2 = Integer.parseInt(nStr.substring(i, j));
				int num3 = Integer.parseInt(nStr.substring(j));

				int newNum = num1 + num2 + num3;
				calculate(newNum, total_cnt + countOdd(newNum));
			}
		}
	}

	static void pro() {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		calculate(N, countOdd(N));

		sb.append(min).append(' ').append(max);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
