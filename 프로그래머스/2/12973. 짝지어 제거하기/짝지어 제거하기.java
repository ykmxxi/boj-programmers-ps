/*  
    짝지어 제거하기
    - 알파벳 소문자로 이루어진 문자열로 시작
    - 같은 알파벳이 2개 붙어 있는 짝을 찾기
    - 그 둘을 제거한 뒤, 앞뒤로 문자열을 이어 붙인다
    - 이 과정을 반복해서 문자열을 모두 제거한다면 짝지어 제거하기가 종료
    
    짝지어 제거하기를 성공적으로 수행할 수 있으면 1, 아닐 경우 0을 리턴
    - 모두 제거하면 1, 아닌 경우 0
    - 스택에 쌓으면서 이전과 같으면 stack에 있는걸 pop
*/

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Stack<String> stack = new Stack<>();
        stack.push(s.substring(0, 1));
        
        for (int i = 1; i < s.length(); i++) {
            String str = s.substring(i, i + 1);
            
            if (stack.isEmpty()) { // 스택이 비어있으면 넣고 다음 문자로 넘어가기
                stack.push(str);
                continue;
            }
            
            if (stack.peek().equals(str)) { // 이전과 같으면
                stack.pop();
            } else {
                stack.push(str);
            }
        }
        
        if (stack.size() == 0) {
            answer = 1;
        }

        return answer;
    }
}