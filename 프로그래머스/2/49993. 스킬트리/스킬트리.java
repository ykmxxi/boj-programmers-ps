import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] arr = skill.toCharArray();
        boolean[] prev = new boolean[26];
        Queue<Character> q = new LinkedList<>();
        
        for (char c : arr) {
            prev[c - 'A'] = true;
            q.offer(c);
        }
        
        for (String s : skill_trees) {
            boolean flag = true;
            for (char c : s.toCharArray()) {
                if (prev[c - 'A']) { // 선행스킬이 존재하면
                    if (q.peek() == c) {
                        q.poll();
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            
            if (flag) {
                answer++;
            }
            q.clear();
            for (char c : arr) {
                q.offer(c);
            }
        }
        
        return answer;
    }
}