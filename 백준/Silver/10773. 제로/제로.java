import java.util.*;
import java.io.*;

// 시간 복잡도: O(K)
// 공간 복잡도: O(K)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int K; // 1 ~ 100,000(10^5)
    static int[] A; // 각 수, 0 ~ 1,000,000(10^6), MAX: 10^11 -> long but 정답 조건은 int 범위 명시

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        K = Integer.parseInt(br.readLine());
        A = new int[K];
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        // 잘못된 수를 부를 떄마다 0을 외쳐서, 가장 최근에 쓴 수를 지우게 만든다
        // 모든 수를 더한 값
        int sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            if (A[i] == 0) {
                if (!stack.isEmpty()) {
                    sum -= stack.pop();
                }
            } else {
                sum += A[i];
                stack.push(A[i]);
            }
        }

        System.out.print(sum);
    }
}
