import java.util.*;
import java.io.*;

// 시간 복잡도:
// 공간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static String[] arr; // 각 문자열 길이: 1 ~ 1,000,000

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
    }

    static void pro() {
        // 키보드 명령을 모두 기록, 화살표나 백스페이스를 입력해도 정확한 비밀번호 유추 가능
        // 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표
        for (String str : arr) {
            sb.append(solve(str)).append('\n');
        }

        System.out.print(sb);
    }

    static String solve(String str) {
        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        char[] chars = str.toCharArray();

        for (char ch : chars) {
            if (ch == '<') {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }
            } else if (ch == '>') {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
            } else if (ch == '-') {
                if (!left.isEmpty()) {
                    left.pop();
                }
            } else {
                left.push(ch);
            }
        }

        StringBuilder sb2 = new StringBuilder();
        while (!left.isEmpty()) {
            sb2.append(left.pollLast());
        }
        while (!right.isEmpty()) {
            sb2.append(right.pop());
        }
        return sb2.toString();
    }
}
