import java.io.*;
import java.util.*;

// 시간 복잡도:
// 공간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int K, N;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // n-1개의 원판을 1 -> 2번 기둥으로 옮긴다
        // n번 원판을 1 -> 3번 기둥으로 옮긴다
        rec(1, 3, N);

        System.out.println(K);
        System.out.print(sb);
    }

    // 원판 n개를 start -> end 기둥으로
    static void rec(int start, int end, int n) {
        if (n == 1) {
            K++;
            sb.append(start).append(' ').append(end).append('\n');
            return;
        }
        // n번 원판을 start -> end 로 옮기려면 n-1개를 6 - end - start로 옮겨야 한다.
        rec(start, 6 - start - end, n - 1);
        sb.append(start).append(' ').append(end).append('\n');
        K++;
        // 다시 n-1개의 원판을 6-start-end 에서 end로
        rec(6-start-end, end, n-1);
    }
}
