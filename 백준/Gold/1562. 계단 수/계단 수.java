import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MOD = 1000000000;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][][][] Dy;

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());

		Dy = new int[N + 1][10][10][10];
	}

	static void pro() {
		for (int num = 1; num <= 9; num++) {
			Dy[1][num][num][num] = 1;
		}

		for (int len = 2; len <= N; len++) {
			for (int prev = 0; prev <= 9; prev++) {
				for (int low = 0; low <= 9; low++) {
					for (int high = 0; high <= 9; high++) {
						for (int cur : new int[] {prev - 1, prev + 1}) {
							if (cur < 0 || cur > 9) {
								continue;
							}

							Dy[len][cur][Math.min(low, cur)][Math.max(high, cur)] += Dy[len - 1][prev][low][high];
							Dy[len][cur][Math.min(low, cur)][Math.max(high, cur)] %= MOD;
						}
					}
				}
			}
		}

		int ans = 0;
		for (int num = 0; num <= 9; num++) {
			ans += Dy[N][num][0][9];
			ans %= MOD;
		}

		System.out.println(ans);
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
