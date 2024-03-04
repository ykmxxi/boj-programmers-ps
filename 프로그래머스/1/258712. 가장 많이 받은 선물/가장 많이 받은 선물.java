/*
    - 다음 달에 누가 선물을 많이 받을지 예측
    - 두 사람이 선물을 주고받은 기록 존재: 두 사람 사이 더 많은 선물을 준 사람이 다음 달에 1개 받음
    - 기록이 없거나 같은 개수를 주고 받으면 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 1개 받음
    - 선물 지수: 준 선물의 수 - 받은 선물의 수
    - 다음 달에 가장 많이 받는 사람의 선물의 수
*/

import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> score = new HashMap<>();
        Map<String, Integer> index = new HashMap<>();
        Map<Integer, String> index2 = new HashMap<>();
        
        int idx = 0;
        for (String f : friends) {
            score.put(f, 0);
            index.put(f, idx);
            index2.put(idx, f);
            idx++;
        }
        
        int len = friends.length;
        int[][] arr = new int[len][len];
        int[] cnt = new int[len];
        
        for (String g : gifts) {
            StringTokenizer st = new StringTokenizer(g, " ");
            String from = st.nextToken();
            String to = st.nextToken();
            
            int a = score.get(from) + 1;
            int b = score.get(to) - 1;
            score.put(from, a);
            score.put(to, b);
            
            int r = index.get(from);
            int c = index.get(to);
            arr[r][c]++;
        }
        
        int answer = 0;
        
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    continue;
                }
                if (arr[i][j] > arr[j][i]) {
                    cnt[i]++;
                } else if (arr[i][j] < arr[j][i]) {
                    cnt[j]++;
                } else {
                    String in = index2.get(i);
                    String jn = index2.get(j);
                    
                    if (score.get(in) > score.get(jn)) {
                        cnt[i]++;
                    } else if (score.get(in) < score.get(jn)) {
                        cnt[j]++;
                    }
                }
            }
        }
        
        for (int c : cnt) {
            answer = Math.max(answer, c);
        }
        return answer;
    }
    
}