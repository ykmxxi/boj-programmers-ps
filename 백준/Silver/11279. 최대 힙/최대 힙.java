import java.util.*;
import java.io.*;

// 시간 복잡도: O(N logN)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        for (int i = 0 ; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int num : A) {
            if (num == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append('\n');
                } else {
                    sb.append(pq.poll()).append('\n');
                }
            } else {
                pq.offer(num);
            }
        }

        System.out.println(sb);
    }
}