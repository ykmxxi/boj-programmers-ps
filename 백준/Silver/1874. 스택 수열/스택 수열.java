// https://www.acmicpc.net/problem/1874
// 시간 복잡도:
// 모든 push/pop 경우를 세는 것은 시간 초과

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    static void pro() {
        // 1 ~ n 스택에 저장, stack push 순서는 반드시 오름차순, 수열을 만들 수 있는지 없는지, 가능하면 어떤 순서로 push(+), pop(-)
        Deque<Integer> dq = new ArrayDeque<>();

        int cur = 1;
        for (int i = 0; i < N; i++) {
            for (int j = cur; j <= N; j++) {
                if (cur <= arr[i]) {
                    dq.push(j);
                    sb.append("+").append("\n");
                    cur++;
                    continue;
                }
                break;
            }

            if (dq.peek() != arr[i]) {
                System.out.println("NO");
                return;
            } else {
                dq.pop();
                sb.append("-").append("\n");
            }
        }
        if (!dq.isEmpty()) {
            System.out.println("NO");
        } else {
            System.out.println(sb);
        }
    }
}
