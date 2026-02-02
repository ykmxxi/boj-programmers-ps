import java.util.*;
import java.io.*;

// 시간 복잡도: O(N), N은 라인의 개수, (문자열의 최대 길이 100 -> 상수)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        while (true) {
            String line = br.readLine();
            if (line.equals(".")) {
                break;
            }
            list.add(line);
        }
    }

    static void pro() {
        // 문자열 (), [] 2종류 균형을 이루어야 한다.
        for (String str : list) {
            Deque<Character> dq = new ArrayDeque<>();
            boolean flag = true;
            for (char ch : str.toCharArray()) {
                if (ch == '(' || ch == '[') {
                    dq.push(ch);
                }
                if (ch == ')') {
                    if (dq.isEmpty() || dq.peekFirst() != '(') {
                        flag = false;
                        break;
                    }
                    dq.pop();
                }
                if (ch == ']') {
                    if (dq.isEmpty() || dq.peekFirst() != '[') {
                        flag = false;
                        break;
                    }
                    dq.pop();
                }
            }

            if (dq.isEmpty() && flag) {
                sb.append("yes").append('\n');
            } else {
                sb.append("no").append('\n');
            }
        }

        System.out.print(sb);
    }
}
