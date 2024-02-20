import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String IN;
	static int[] Dy;
	static int length, MOD = 1000000;

	static void input() throws IOException {
		IN = br.readLine();
		length = IN.length();
	}

	static boolean check(char front, char back) { // 2자리 암호가 되는지 check
		if (front == '1') {
			return true;
		}
		if (front == '2' && back <= '6') {
			return true;
		}
		return false;
	}

	static void pro() {
		Dy = new int[length];

		// 초기값 구하기
		if (IN.charAt(0) != '0')
			Dy[0] = 1;

		// 점화식을 토대로 Dy 배열 채우기
		for (int i = 1; i < length; i++) {
			// i 번 숫자를 단독으로 해석 가능할 때
			if (IN.charAt(i) != '0')
				Dy[i] = Dy[i - 1];

			// i - 1번과 i 번 숫자를 하나의 문자로 해석 가능할 때
			if (check(IN.charAt(i - 1), IN.charAt(i))) {
				if (i >= 2)
					Dy[i] += Dy[i - 2];
				else
					Dy[i] += 1;
				// Dy[i] %= MOD;
			}
			Dy[i] %= MOD;
		}

		// Dy배열로 정답 계산하기
		System.out.println(Dy[length - 1]);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
