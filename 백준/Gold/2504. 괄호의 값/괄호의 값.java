/*

 */

import java.util.*;
import java.io.*;

public class Main {

    static String s;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
    }

    static void pro() {
        Deque<Character> dq = new ArrayDeque<>();
        int ans = 0;
        int val = 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dq.push(s.charAt(i));
                val *= 2;
            } else if (s.charAt(i) == '[') {
                dq.push(s.charAt(i));
                val *= 3;
            } else if (s.charAt(i) == ')') {
                if (dq.isEmpty() || dq.peek() != '(') {
                    ans = 0;
                    break;
                } else if (s.charAt(i - 1) == '(') {
                    ans += val;
                }
                dq.pop();
                val /= 2;
            } else if (s.charAt(i) == ']') {
                if (dq.isEmpty() || dq.peek() != '[') {
                    ans = 0;
                    break;
                } else if (s.charAt(i - 1) == '[') {
                    ans += val;
                }
                dq.pop();
                val /= 3;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (!dq.isEmpty()) {
            sb.append(0);
        } else {
            sb.append(ans);
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

}
