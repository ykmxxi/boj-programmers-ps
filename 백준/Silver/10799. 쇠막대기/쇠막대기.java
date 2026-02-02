import java.util.*;
import java.io.*;

// 시간 복잡도: O(N), 길이(N: 1 ~ 100,000)
// 공간 복잡도: O(N)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String str;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        str = br.readLine();
    }

    static void pro() {
        // 자신보다 긴 쇠막대기 위에만 놓을 수 있음
        int ans = 0;
        char prev = '(';
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                prev = '(';
            } else if (ch == ')') {
                if (!stack.isEmpty() && prev == '(') { // 레이저
                    stack.pop(); // 레이저 여는 괄호 제거 후
                    ans += stack.size();
                } else { // 레이저가 아님
                    if (!stack.isEmpty()) {
                        stack.pop();
                        ans++;
                    }
                }
                prev = ')';
            }
        }

        System.out.print(ans);
    }
}
