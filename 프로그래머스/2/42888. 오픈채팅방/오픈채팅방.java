 /*
    - "[닉네임]님이 들어왔습니다.", "[닉네임]님이 나갔습니다."
    - 닉네임을 변경하는 방법: 채팅방을 나간 후 새로운 닉네임으로 들어온다, 채팅방에서 닉네임을 변경한다
*/

import java.util.*;

class Solution {
    
    public String[] solution(String[] record) {
        Map<String, String> name = new HashMap<>(); // key:uuid, value:name
        Queue<Info> q = new LinkedList<>(); // message 큐
        
        for (String r : record) {
            String[] token = r.split(" ");
            String command = token[0];
            
            if ("Enter".equals(command)) {
                name.put(token[1], token[2]);
                q.offer(new Info(token[1], true));
            } else if ("Change".equals(command)) {
                name.put(token[1], token[2]);
            } else {
                q.offer(new Info(token[1], false));
            }
        }
        
        String[] answer = new String[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            Info info = q.poll();
            
            StringBuilder sb = new StringBuilder();
            if (info.in) {
                sb.append(name.get(info.uuid)).append("님이 들어왔습니다.");
            } else {
                sb.append(name.get(info.uuid)).append("님이 나갔습니다.");
            }
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
    
    static class Info {
        String uuid;
        boolean in; // 들어오면 true, 나가면 false
        
        Info(String uuid, boolean in) {
            this.uuid = uuid;
            this.in = in;
        }
    }
}
