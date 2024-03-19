import java.util.*;

class Solution {
    
    int max;
    
    void find(String s, int start, int end) {
        int L = start;
        int R = end;
        
        while (L >= 0 && R < s.length()) {
            if (s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            } else {
                break;
            }
            
            if (max < R - L - 1) {
                max = R - L - 1;
            }
        }
    }
    
    public int solution(String s) {
        int len = s.length();
        
        if (len < 2) { // 길이가 1인 문자열은 그대로 반환
            return 1;
        }
        if (len == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return 2;
            } else {
                return 1;
            }
        }
        
        max = 0;
        for (int i = 0; i < len - 1; i++) { // 팰린드롬이 되려면 최소 길이가 2 or 3 이상
            find(s, i, i + 1); // aa 길이가 짝수인 팰린드롬
            find(s, i, i + 2); // aba 길이가 홀수인 팰린드롬
        }

        return max;
    }
    
}
