import java.util.*;
import java.io.*;

// 시간 복잡도: O(N*M) // N = 명령어의 개수, M = 문자열의 길이
// 공간 복잡도: O(100,000) + O(600,000)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String str;
    static String[] A;
    static int N;
    static Deque<Character> left, right;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        str = br.readLine();
        N = Integer.parseInt(br.readLine());
        A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
    }

    static void pro() {
        // 최대 600,000 길이 글자
        // 맨 앞, 맨 뒤, 문장 중간 -> 길이가 L 문자열에 커서가 위치할 수 있는 곳은 L + 1
        // L(왼쪽 1칸), D(오른쪽 한 칸), B(커서 왼쪽 문자 삭제), P $ ($ 문자를 커서 왼쪽에 추가)
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();

        for (char ch : str.toCharArray()) {
            left.push(ch);
        }

        for (String com : A) { // O(100,000)
            command(com);
        }

        while (!left.isEmpty()) {
            right.push(left.pop());
        }
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.print(sb);
    }

    static void command(String com) {
        if (com.equals("L")) {
            if (!left.isEmpty()) {
                right.push(left.pop());
            }
        } else if (com.equals("D")) {
            if (!right.isEmpty()) {
                left.push(right.pop());
            }
        } else if (com.equals("B")) {
            if (!left.isEmpty()) {
                left.pop();
            }
        } else {
            left.push(com.charAt(2));
        }
    }
}
