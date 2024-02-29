/*
    https://school.programmers.co.kr/learn/courses/30/lessons/76502
    
    - 올바른 괄호 문자열
        - (), [], {} -> 모두 올바른 괄호 문자열
        - 만약 A가 올바른 괄호 문자열이라면, (A), [A], {A} 도 올바른 괄호 문자열
            ex) [] 가 올바른 괄호 문자열이라면 ([]), [[]], {[]} 도 올바른 괄호 문자열
        - 만약 A, B가 올바른 괄호 문자열이라면 AB도 올바른 괄호 문자열
            ex) [], ({}) 가 올바른 괄호 문자열이라면 []({}) 도 올바른 괄호 문자열
*/

import java.util.*;

class Solution {
    
    static boolean check(String str) {
        Deque<Character> dq = new ArrayDeque<>();
        int cnt = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (cnt < 0) {
                return false;
            }
            
            if (ch == '(' || ch == '{' || ch == '[') {
                cnt++;
            } else {
                cnt--;
            }
            
            if (dq.isEmpty()) {
                dq.push(ch);
                continue;
            }
            
            if (ch == ')' && dq.peek() == '(') {
                dq.pop();
                continue;
            }
            
            if (ch == ']' && dq.peek() == '[') {
                dq.pop();
                continue;
            }
            
            if (ch == '}' && dq.peek() == '{') {
                dq.pop();
                continue;
            }
            
            dq.push(ch);
        }
        
        return dq.size() == 0;
    }
    
    public int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i).concat(s.substring(0, i));
            
            if (check(str)) {
                answer++;
            }
        }
        
        return answer;
    }
}