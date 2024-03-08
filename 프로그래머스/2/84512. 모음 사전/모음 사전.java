/*
    - 사전에 알파벳 모음 'A' 'E' 'I' 'O' 'U' 만 사용해 만들 수 있는 길이 5 이하의 모든 단어가 수록
    - 첫 번째 단어: "A" 두 번째 단어: "AA", 마지막 단어: "UUUUU"
    - 단어가 주어질 때 몇 번째 단어인지 return
    - 모든 경우의 수: 5 + 5^2 + 5^3 + 5^4 + 5^5 = 3905 가지 경우의 수 -> 완탐 가능
*/

import java.util.*;

class Solution {
    
    String[] arr = {"A", "E", "I", "O", "U"};
    List<String> list;
    int ans;
    
    void rec(String target, String s, int depth) {
        list.add(s);
        
        if (s.equals(target)) {
            ans = list.size() - 1;
            return;
        }
        
        if (depth == 5) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            rec(target, s + arr[i], depth + 1);
        }
        
    }
    
    public int solution(String word) {
        list = new ArrayList<>();
        ans = 0;
        
        rec(word, "", 0);
        
        return ans;
    }
}