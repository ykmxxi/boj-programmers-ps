import java.io.*;
import java.util.*;

// 시간 복잡도: O(1)
// 공간 복잡도: O(1)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[] dy;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        pre();
        while (T-- > 0) {
            input();
            pro();
        }
        System.out.print(sb);
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        sb.append(dy[N]).append('\n');
    }

    static void pre() {
        // N을 1, 2, 3의 합으로 나타내기
        dy = new int[11];
        dy[0] = 1;
        dy[1] = 1;
        dy[2] = 2;

        for (int i = 3; i <= 10; i++) {
            dy[i] = dy[i - 1] + dy[i - 2] + dy[i - 3];
        }
    }
}
