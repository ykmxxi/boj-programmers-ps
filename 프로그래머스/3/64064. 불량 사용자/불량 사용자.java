/*
    - 아이디에 '*' 문자를 최소 하나 이상 사용
    - 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한지 세기
    - 각 ban에 매칭되는 아이디의 인덱스를 구해서 조합
*/

import java.util.*;

class Solution {
    
    int cnt;
    boolean[] used;
    Set<String> set;
    
    void rec(int idx, Map<String, List<Integer>> map, String[] banned_id, int target, String s) {
        if (idx == target) {
            List<Integer> list = new ArrayList<>();
            for (char c : s.toCharArray()) {
                list.add(c - '0');
            }
            Collections.sort(list);
            
            String tmp = "";
            for (int n : list) {
                tmp += n;
            }
            
            if (!set.contains(tmp)) {
                set.add(tmp);
                cnt++;
            }
            
        } else {
            for (int n : map.get(banned_id[idx])) {  
                if (!used[n]) {
                    used[n] = true;
                    rec(idx + 1, map, banned_id, target, s + n);
                    used[n] = false;
                }
            }
        }
        
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 1;
        
        Map<String, List<Integer>> map = new HashMap<>(); // key:ban value:user_id의 인덱스
        
        for (String ban : banned_id) {
            int cnt = 0;
            List<Integer> list = new ArrayList<>();
            
            int len = ban.length();
            for (int idx = 0; idx < user_id.length; idx++) {
                boolean can = true;
                if (len != user_id[idx].length()) {
                    continue;
                }
                for (int i = 0; i < len; i++) {
                    if (ban.charAt(i) == '*') {
                        continue;
                    }
                    if (ban.charAt(i) != user_id[idx].charAt(i)) {
                        can = false;
                        break;
                    }
                }
                
                if (can) {
                    list.add(idx);
                }
            }
            
            if (!map.containsKey(ban)) {
                map.put(ban, list);
            }

        }
        
        cnt = 0;
        used = new boolean[user_id.length];
        set = new HashSet<>();
        
        rec(0, map, banned_id, banned_id.length, "");
        
        
        return cnt;
    }
    
}
