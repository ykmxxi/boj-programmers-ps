/*
    전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인
    구조대: 119
    영석: 11 9552 4421 -> 구조대 전화번호는 영석이의 전화번호의 접두사
    
    어떤 번호가 다른 번호의 접두어인 경우가 있으면 false, 아니면 true를 return
*/

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();
        
        for (String str : phone_book) {
            set.add(str);
        }
        
        for (String str : phone_book) {
            for (int i = 1; i < str.length(); i++) {
                if (set.contains(str.substring(0, i))) {
                    return false;
                }
            }
        }
    
        return true;
    }
}