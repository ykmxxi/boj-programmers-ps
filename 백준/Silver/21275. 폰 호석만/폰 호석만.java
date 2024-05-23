import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static String aNum, bNum;
	static long MAX = Long.MAX_VALUE;

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		aNum = st.nextToken();
		bNum = st.nextToken();
	}

	static int conv(char ch) { // 알파벳을 10진법으로 변환
		if ('0' <= ch && ch <= '9') {
			return (ch - '0');
		}
		return ch - 'a' + 10;
	}

	static long possible(String str, int base) {
		long res = 0;
		for (char c : str.toCharArray()) {
			if (conv(c) >= base) {
				return -1;
			}

			// 원래 식 res * base + conv(c) 는 오버 플로우 발생 시 MAX 보다 큰지 확인할 수 없어 이항을 시킨다
			if (res > (MAX - conv(c)) / base) {
				return -1;
			}
			res = res * base + conv(c);
		}
		return res;
	}

	static void pro() {
		long answerX = -1, answerA = 0, answerB = 0;

		for (int A = 2; A <= 36; A++) {
			long valueA = possible(aNum, A);
			if (valueA == -1) {
				continue;
			}

			for (int B = 2; B <= 36; B++) {
				if (A == B) {
					continue;
				}

				long valueB = possible(bNum, B);
				if (valueB == -1) {
					continue;
				}

				if (valueA != valueB) {
					continue;
				}

				if (answerX == -1) {
					answerX = valueA;
					answerA = A;
					answerB = B;
				} else {
					System.out.println("Multiple");
					return;
				}
			}

		}

		if (answerX == -1) {
			sb.append("Impossible");
		} else {
			sb.append(answerX).append(' ').append(answerA).append(' ').append(answerB);
		}

		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
