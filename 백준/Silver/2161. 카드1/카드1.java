// https://www.acmicpc.net/problem/2161
// 시간 복잡도: O(N)

import java.io.*;
import java.util.*;

public class Main {

    static int N; // 1 <= N <= 1,000
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void pro() {
        // 제일 위 카드를 바닥에 버린다 -> 제일 위 카드를 제일 아래에 있는 카드 밑으로 옮긴다
        // 1. 제일 위 카드를 버린다
        // 2. 위 카드를 아래로 옮긴다
        // 3. 1, 2번 과정을 반복한다
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        while (!dq.isEmpty()) {
            if (dq.size() == 1) {
                sb.append(dq.poll());
                break;
            }
            sb.append(dq.pollFirst()).append(' ');

            dq.offerLast(dq.pollFirst());
        }
        System.out.println(sb);
    }
}
