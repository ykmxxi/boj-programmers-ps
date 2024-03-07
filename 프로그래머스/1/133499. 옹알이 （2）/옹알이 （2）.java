/*
    - "aya" "ye" "woo" "ma" 네 가지 발음 + 네 가지 발음을 조합해서 만들 수 있는 발음만 가능
    - 조카가 발음할 수 있는 단어의 개수를 return
*/

import java.util.*;

class Solution {
    
    static boolean can(String s) {
        StringBuilder sb = new StringBuilder();
        
        List<String> word = List.of("aya", "ye", "woo", "ma");
        String before = "";
        
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            
            if (!before.equals(sb.toString()) && word.contains(sb.toString())) {
                before = sb.toString();
                sb = new StringBuilder();
            }
        }
        
        return sb.toString().isEmpty();
        
    }
    
    public int solution(String[] babbling) {
        int ans = 0;
        
        for (String s : babbling) {
            if (can(s)) {
                ans++;
            }
        }
        
        return ans;
    }
}