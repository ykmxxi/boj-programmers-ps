import java.util.*;
import java.io.*;

// 시간 복잡도: O(1)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N; // 1 ~ 1,000,000

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // 숫자 0 - 9 한 세트 -> 방 번호에 맞는 필요한 세트의 개수
        // 6 -> 9, 9 -> 6 뒤집어서 사용가능
        int[] A = new int[10];
        while (N > 0) {
            A[N % 10]++;
            N /= 10;
        }
        int ans = 0;
        int sum = 0; // 6, 9의 개수
        for (int i = 0; i <= 9; i++) {
            if (i == 6 || i == 9) {
                sum += A[i];
                continue;
            }
            ans = Math.max(ans, A[i]);
        }
        if (sum % 2 == 0) {
            ans = Math.max(ans, sum / 2);
        } else {
            ans = Math.max(ans, (sum / 2) + 1);
        }
        System.out.print(ans);
    }
}
