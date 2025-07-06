// https://www.acmicpc.net/problem/2839
// 시간 복잡도: O(1)

import java.io.*;

public class Main {

    static int size = 5001;
    static int N; // 3 <= N <= 5,000

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        int min = Integer.MAX_VALUE;
        int cnt = N / 5;
        while (cnt > -1) {
            int remain = N - (cnt * 5);
            if (remain % 3 != 0) {
                cnt--;
                continue;
            }
            min = Math.min(min, (remain / 3) + cnt);
            cnt--;
        }
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }
}
