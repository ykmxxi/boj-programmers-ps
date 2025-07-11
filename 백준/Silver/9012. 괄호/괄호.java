// https://www.acmicpc.net/problem/9012
// 시간 복잡도: O(T * N), (20억 * 50 -> 최악의 경우 100초)완탐하면 시간초과 -> 전처리, 자료구조 활용해 올바른 괄호가 아니면 바로 끝내기

// 올바른 괄호 -> YES, 아니면 NO

import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static String[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        arr = new String[T];
        for (int i = 0; i < T; i++) {
            arr[i] = br.readLine();
        }
    }

    static void pro() {
        for (String st : arr) {
            // st.length == 홀수 -> NO
            if (st.length() % 2 == 1) {
                sb.append("NO").append("\n");
            } else {
                Deque<Character> dq = new ArrayDeque<>();
                boolean flag = false;
                for (char ch : st.toCharArray()) {
                    if (ch == '(') {
                        dq.push(ch);
                    } else {
                        if (dq.isEmpty()) {
                            sb.append("NO").append("\n");
                            flag = true;
                            break;
                        }
                        dq.pop();
                    }
                }
                if (flag) {
                    continue;
                }
                if (dq.isEmpty()) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
