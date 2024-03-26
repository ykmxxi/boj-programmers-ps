/*  
    - JadenCase: 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열
    - 단 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자
*/

import java.util.*;

class Solution {
    public String solution(String s) {
        s = s.toLowerCase();
        
        StringTokenizer st = new StringTokenizer(s, " ", true);
        StringBuilder sb = new StringBuilder();
        
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            
            if (token.equals(" ")) {
                sb.append(token);
            } else {
                sb.append(token.substring(0, 1).toUpperCase().concat(token.substring(1)));
            }
        }
        
        return sb.toString();
    }
    
}
