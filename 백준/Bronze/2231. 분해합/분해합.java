import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        // N의 분해합 = N과 N을 이루는 각 자리수의 합
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            int num = i;
            int sum = i;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            if (sum == N) {
                ans = i;
                break;
            }
        }
        System.out.print(ans);
    }
}
