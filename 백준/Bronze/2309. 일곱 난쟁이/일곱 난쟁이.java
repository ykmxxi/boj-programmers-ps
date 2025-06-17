import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int[] A;

	static void input() throws IOException {
		A = new int[10];
		for (int i = 1; i <= 9; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
	}

	static boolean isHundred(int idx1, int idx2) {
		int sum = 0;
		for (int i = 1; i <= 9; i++) {
			if (i == idx1 || i == idx2)
				continue;
			sum += A[i];
		}

		return sum == 100;
	}

	static void pro() {
		Arrays.sort(A);
		int idx1 = 0;
		int idx2 = 0;

		for (int i = 1; i < 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				if (isHundred(i, j)) {
					idx1 = i;
					idx2 = j;
					break;
				}
			}
		}

		for (int i = 1; i <= 9; i++) {
			if (i == idx1 || i == idx2)
				continue;
			sb.append(A[i]).append('\n');
		}

		System.out.print(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
