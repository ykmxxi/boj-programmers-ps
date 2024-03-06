/*
    - LZW 압축
    1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화
    2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾기
    3. w에 해당하는 사전의 색인 번호를 출력, 입력에서 w 제거
    4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c) w + c 단어를 사전에 등록
    5. 2단계로 돌아간다
*/

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> m = new HashMap<>();
        
        for (int i = 0; i < 26; i++) {
            char tmp = (char) ('A' + i);
            m.put(Character.toString(tmp), i + 1);
        }
        
        Set<String> keys = new HashSet<>(m.keySet());
        boolean[] visit = new boolean[msg.length()];
        
        int id = 27;
        
        for (int i = 0; i < msg.length(); i++) {
            if (visit[i]) {
                continue;
            }
            
            String cur = msg.substring(i, i + 1);
            visit[i] = true;
            
            if (keys.contains(cur)) { // 더 긴것이 있는지 확인
                int last = i;
                
                StringBuilder sb = new StringBuilder();
                sb.append(msg.charAt(i));
                
                for (int j = i + 1; j < msg.length(); j++) {
                    sb.append(msg.charAt(j));
                    if (keys.contains(sb.toString())) {
                        cur = sb.toString(); // 긴것이 존재해 갱신
                        visit[j] = true; // 방문 처리
                        last = j;
                    }
                }
                
                ans.add(m.get(cur));
                if (last + 1 < msg.length()) {
                    sb = new StringBuilder();
                    sb.append(cur).append(msg.charAt(last + 1));
                    m.put(sb.toString(), id);
                    keys.add(sb.toString());
                    id++;
                }
            }
        }
        
        
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}