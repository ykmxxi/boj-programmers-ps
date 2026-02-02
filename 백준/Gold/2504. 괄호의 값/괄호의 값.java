import java.util.*;
import java.io.*;

// 시간 복잡도: O(N)
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
        // (), [] 같이 한 쌍의 괄호로만 이루어지면 올바름
        // (X), [X] 도 올바른 괄호열, XY도 올바른 괄호열
        Deque<Character> dq = new ArrayDeque<>();
        int ans = 0;
        int val = 1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                val *= 2;
                dq.push(ch);
            } else if (ch == '[') {
                val *= 3;
                dq.push(ch);
            } else if (ch == ')') {
                if (dq.isEmpty() || dq.peek() != '(') {
                    ans = 0;
                    break;
                }
                if (str.charAt(i - 1) == '(') {
                    ans += val;
                }
                dq.pop();
                val /= 2;
            } else {
                if (dq.isEmpty() || dq.peek() != '[') {
                    ans = 0;
                    break;
                }
                if (str.charAt(i - 1) == '[') {
                    ans += val;
                }
                dq.pop();
                val /= 3;
            }
        }

        if (!dq.isEmpty()) {
            System.out.print(0);
        } else {
            System.out.print(ans);
        }
    }
}
