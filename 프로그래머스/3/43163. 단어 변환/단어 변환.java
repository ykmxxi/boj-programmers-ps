/*
    - begin, target 단어와 단어의 집합 words 존재, 규칙을 적용해 begin -> target 변환 가장 짧은 과정
    - 규칙 1. 한 번에 한 개의 알파벳만 바꿀 수 있음
    - 규칙 2. words에 있는 단어로만 변환 가능
    - 각 단어의 길이는 모두 같음
*/

import java.util.*;

class Solution {
    
    static class Info {
        String s;
        int dist;
        
        Info(String s, int dist) {
            this.s = s;
            this.dist = dist;
        }
    }
    
    static int bfs(String start, List<String> words, String target) {
        boolean[] visit = new boolean[words.size()];
        Queue<Info> q = new LinkedList<>();
        int len = start.length();
        int min = 0;
        
        q.offer(new Info(start, 0));
        
        while (!q.isEmpty()) {
            Info info = q.poll();
            String x = info.s;
            int d = info.dist;
            
            if (x.equals(target)) {
                min = d;
                break;
            }
            
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < words.size(); j++) {
                    if (visit[j]) { // 이미 변환한적 있는 단어면 넘어가기
                        continue;
                    }
                    
                    boolean flag = true; // 변환 가능 여부
                    for (int k = 0; k < len; k++) {
                        if (i == k) {
                            continue;
                        }
                        if (words.get(j).charAt(k) != x.charAt(k)) {
                            flag = false;
                            break;
                        }
                    }
                    
                    if (flag) { // 변환 가능하면
                        visit[j] = true;
                        q.offer(new Info(words.get(j), d + 1));
                    }
                }
            }
        }
        
        return min;
    }
    
    public int solution(String begin, String target, String[] words) {
        List<String> w = new ArrayList<>(Arrays.asList(words));
        if (!w.contains(target)) { // words에 target이 없으면 못바꿈
            return 0;
        }
        
        // 한 글자씩 변경하며 찾기
        return bfs(begin, w, target);
        
    }
}