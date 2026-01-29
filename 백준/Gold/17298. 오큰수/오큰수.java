import java.util.*;
import java.io.*;

// 시간 복잡도: O(N), N 보다 크지만 N에 수렴
// 공간 복잡도: O(2N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] A; // len, 1 ~ 1,000,000

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {
        // 오큰수: NGE(i), A(i)의 오큰수는 오른쪽에 있으면서 A(i)보다 큰 수 중에서 가장 왼쪽에 있는 수, 없으면 -1
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) { // 현재 인덱스 스택에서 A[i]보다 작은 녀석들 오큰수 지정
                ans[stack.pop()] = A[i];
            }
            stack.push(i); // 인덱스 추가
        }

        while (!stack.isEmpty()) { // 여기까지 존재하는 인덱스 값들은 오큰수 X
            ans[stack.pop()] = -1;
        }

        for (int n : ans) {
            sb.append(n).append(' ');
        }
        System.out.print(sb);
    }
}
