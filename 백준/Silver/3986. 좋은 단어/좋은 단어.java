// https://www.acmicpc.net/problem/3986

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, ans;
    static String[] arr;
    static Deque<Character> dq;

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.write(String.format("%d%n", ans));
        bw.flush();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0 ; i < N; i++) {
            arr[i] = br.readLine();
        }
    }

    static void pro() {
        // 좋은 단어: ABBA / 안좋은 단어: ABAB, AAA
        for (int i = 0 ; i < N; i++) {
            dq = new ArrayDeque<>();
            for (char ch : arr[i].toCharArray()) {
                if (!dq.isEmpty() && dq.peek() == ch) {
                    dq.pop();
                } else {
                    dq.push(ch);
                }
            }
            if (dq.isEmpty()) {
                ans++;
            }
        }
    }
}
