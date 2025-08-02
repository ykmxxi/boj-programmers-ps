// 문자열 폭발: https://www.acmicpc.net/problem/9935
// 시간 복잡도

import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Deque<Character> dq;
    static String line, bomb;

    public static void main(String[] args) throws IOException {
        input();
        pro();

        bw.flush();
    }

    static void input() throws IOException {
        line = br.readLine();
        dq = new ArrayDeque<>();
        bomb = br.readLine();
    }

    static void pro() throws IOException {
        // 폭발 문자열을 포함하면 모든 폭발 문자열이 폭발, 남은 문자열을 순서대로 이어 붙임
        // 새로 생긴 문자열에도 폭발 문자열이 포함될 수 있음
        int bl = bomb.length();
        char last = bomb.charAt(bl-1);
        for (char ch : line.toCharArray()) {
            dq.offerLast(ch);
            if (ch == last && dq.size() >= bl) {
                boolean flag = true;
                char[] arr = new char[bl];
                for (int i=bl-1; i>=0; i--) { // 뒤부터 꺼내서
                    arr[i] = dq.pollLast();
                    if (arr[i] != bomb.charAt(i)) {
                        flag = false;
                    }
                }
                if (!flag) {
                    for (char ch2 : arr) {
                        dq.offerLast(ch2);
                    }
                }
            }
        }
        // 폭발 문자열이 없을때까지 계속 반복, 남아있는 문자열이 없으면 "FRULA"
        if (dq.isEmpty()) {
            bw.write("FRULA");
        } else {
            for (char ch : dq) {
                bw.write(ch);
            }
        }

    }
}
