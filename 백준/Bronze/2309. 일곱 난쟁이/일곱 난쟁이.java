import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static boolean flag = false;
    static int[] A;
    static int[] B;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        A = new int[9];
        B = new int[7];
        for (int i = 0; i < 9; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        // BF: 조합 문제 9명 중 7개 중복없이 선택
        Arrays.sort(A);
        rec(0, -1, 0);
    }

    static void rec(int len, int prev, int sum) {
        if (flag) {
            return;
        }
        if (len == 7) {
            if (sum == 100) {
                flag = true;
                for (int num : B) {
                    sb.append(num).append('\n');
                }
                System.out.print(sb);
            }
            return;
        }
        for (int i = prev + 1; i < 9; i++) {
            B[len] = A[i];
            rec(len + 1, i, sum + A[i]);
            B[len] = 0;
        }
    }
}
