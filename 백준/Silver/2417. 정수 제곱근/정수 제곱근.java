// 정수 제곱근: https://www.acmicpc.net/problem/2417
// 시간 복잡도: O(logN)

import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long n;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        n = Long.parseLong(br.readLine());
    }

    static void pro() {
        // 정수 제곱근: q^2 >= n 가장 작은 양수 구하기
        // 제곱근이 떨어지는 경우 / 제곱근이 double 인 경우
        long L = 1;
        long R = n; // 최대값의 제곱근
        long ans = 0L;
        if (n == 0L) {
            ans = 0L;
        } else {
            while (L <= R) {
                long mid = (L + R) >>> 1; // 오른쪽으로 비트를 1칸씩 부호없이 옮기면 나누기 2
                if (check(mid)) { // mid^2 가 n 보다 큼?
                    ans = mid;
                    R = mid - 1; // 오른쪽 구간 줄여나가면서 최대값 탐색
                } else {
                    L = mid + 1;
                }
            }
        }
        System.out.println(ans);
    }

    static boolean check(long mid) {
        // mid * mid > div -> mid > div / mid
        // 4 처럼 딱 정수 제곱근으로 떨어지는 경우를 고려해야 한다
        long val = n / mid;
        if (mid > val) { // overflow 처리
            return true;
        }
        if (mid < val) {
            return false;
        }
        return mid * mid >= n;
    }
}
