/*
    - 괄호가 개수는 맞지만 짝이 맞지 않은 형태로 작성되어 있음
    - 모든 괄호를 뽑아서 올바른 순서대로 배치된 괄호 문자열을 알려주기
    - '(' ')' 로 이루어진 문자열 -> 각 개수가 같으면 균형잡힌 괄호 문자열, 짝도 모두 맞을 경우 올바른 괄호 문자열
    - 균형잡힌 괄호 문자열 이라면 다음 과정을 통해 올바른 괄호 문자열로 변환 가능
        1. 빈 문자열인 경우 빈 문자열 반환
        2. 두 균형잡힌 괄호 문자열 u, v로 분리 단 u는 균형잡힌 괄호 문자열로 더 이상 분리 X, v는 빈 문자열 가능
        3. u가 올바른 괄호 문자열이면 v에 대해 1단계 부터 다시 시작해 결과를 u에 이어 붙인 후 반환
        4. u가 올바른 괄호 문자열이 아니면 아래 과정 수행
            a. 빈 문자열에 첫 번째 문자로 ( 붙이기
            b. v에 대해 1단계부터 재귀적으로 수행할 결과 문자열 이어 붙이기
            c. )를 다시 붙임
            d. u의 첫 번째와 마지막 문자를 제거, 나머지 문자들의 괄호 방향을 뒤집어 뒤에 붙임
            e. 생성된 문자열 반환
    - p는 균형잡힌 괄호 문자열
*/

import java.util.*;

class Solution {
    
    boolean check(String p) {
        if (p.isEmpty()) {
            return true;
        }
        
        Deque<Character> dq = new ArrayDeque<>();
        for (char c : p.toCharArray()) {
            if (c == '(') {
                dq.push(c);
            } else {
                if (dq.isEmpty()) {
                    return false;
                } else {
                    dq.pop();
                }
            }
        }
        
        return dq.isEmpty();
    }
    
    String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        
        return sb.toString();
    }
    
    public String solution(String p) {
        String answer = "";
        
        // 올바른 괄호 문자열이면 그대로 반환, 빈 문자열도 그대로 반환
        if (check(p)) {
            return p;
        }
        
        // 두 균형잡힌 문자열 u, v로 분리
        int L = 0, R = 0, idx = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                L++;
            } else {
                R++;
            }
            if (L == R) {
                idx = i;
                break;
            }
        }
        
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);
        
        // 3. u가 올바른 괄호 문자열이면
        if (check(u)) {
            return u + solution(v);
        }
        
        // 4. 올바른 괄호 문자열이 아니면
        return "(" + solution(v) + ")" + reverse(u);
    }
    
}
