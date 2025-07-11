// https://www.acmicpc.net/problem/10799
// 시간 복잡도: O(n), n <= 100,000
// 쇠막대기를 겹쳐 자른다, 쇠막대기는 자신보다 긴 것 위에만 놓인다 / 적어도 하나 잘라야 한다 / 레이저는 쇠막대기 양 끝점과 겹치지 않음
// 레이저: 인접한 ()
// 쇠막대기: 왼쪽 끝 (, 오른쪽 끝 ) -> 단, 레이저는 레이저는 쇠막대기 양 끝점과 겹치지 않음

import java.util.*;
import java.io.*;

public class Main {

    static String S;
    static Deque<Character> dq, dq2;

    public static void main(String[] args) throws IOException {
        input();

        pro();
    }

    static void input() throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         S = br.readLine();
         dq = new ArrayDeque<>();
         dq2 = new ArrayDeque<>();
         for (char ch : S.toCharArray()) {
             dq.push(ch);
         }
    }

    static void pro() {
        // 레이저를 만나면 -> 닫히지 않는 괄호의 개수만큼 짤려 추가
        // 레이저가 아닌 쇠막대기 왼쪽 끝을 만나면 1개 추가
        int ans = 0;
        char before = ' ';
        while (!dq.isEmpty()) {
            char cur = dq.poll();
            if (cur == '(' && before == ')') { // 레이저
                dq2.poll();
                ans += dq2.size(); // 레이저로 자름
            } else if (cur == '(' && before != ')') { // 쇠막대기
                dq2.poll();
                ans++; // 쇠막대기 왼쪽 끝에 도달해 1개 추가
            } else {
                dq2.push(cur);
            }
            before = cur;
        }

        System.out.println(ans);
    }
}
