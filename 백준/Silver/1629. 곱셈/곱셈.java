import java.io.*;
import java.util.*;

// 시간 복잡도: O(log B)
// 공간 복잡도: O(log B)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int A, B, C;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    static void pro() {
        System.out.print(pow(A, B, C));
    }

    static long pow(int a, int b, int c) {
        if (b == 1) {
            return a % c;
        }
        long value = pow(a, b/2, c);
        value = (value * value) % c;
        if (b % 2 == 0) {
            return value;
        }
        return (value * a) % c;
    }
}
