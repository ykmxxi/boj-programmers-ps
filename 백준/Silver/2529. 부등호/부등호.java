import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int k;
	static int[] visit, signs, output;
	static boolean flag;

	static void input() throws IOException {
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		signs = new int[k]; // >: 1. <:0
		for (int i = 0; i < k; i++) {
			if (st.nextToken().equals(">")) {
				signs[i] = 1;
			}
		}
	}

	static boolean canNext(int digit, int cand) {
		if (signs[digit - 1] == 1) { // >
			return output[digit - 1] > cand;
		} else {
			return output[digit - 1] < cand;
		}
	}

	static void rec_func_min(int digit) {
		if (digit == k + 1) {
			for (int num : output) {
				sb.append(num);
			}
			sb.append('\n');
			flag = true;

			return;
		}

		for (int candidate = 0; candidate <= 9; candidate++) {
			if (visit[candidate] == 0 && !flag) {
				if (digit == 0 || canNext(digit, candidate)) {
					visit[candidate] = 1;
					output[digit] = candidate;

					rec_func_min(digit + 1);
					visit[candidate] = 0;
					output[digit] = 0;
				}
			}
		}

	}

	static void rec_func_max(int digit) {
		if (digit == k + 1) {
			for (int num : output) {
				sb.append(num);
			}
			sb.append('\n');
			flag = true;

			return;
		}
		for (int candidate = 9; candidate >= 0; candidate--) {
			if (visit[candidate] == 0 && !flag) {
				if (digit == 0 || canNext(digit, candidate)) {
					visit[candidate] = 1;
					output[digit] = candidate;

					rec_func_max(digit + 1);
					visit[candidate] = 0;
					output[digit] = 0;
				}
			}
		}

	}

	static void pro() {
		visit = new int[10];
		output = new int[k + 1];
		rec_func_max(0);

		flag = false;
		visit = new int[10];
		output = new int[k + 1];
		rec_func_min(0);

		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}

}
