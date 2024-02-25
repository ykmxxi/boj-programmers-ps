/*
    https://school.programmers.co.kr/learn/courses/30/lessons/87946
    
    # 피로도
    - 피로도 시스템(0 이상의 정수)이 있으며, 일정 피로도를 사용해서 던전을 탐험할 수 있다
    - 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도", "소모 피로도"가 존재
    - 하루에 한 번씩 탐험할 수 있는 던전이 여러개 존재
    - 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 한다
    - k: 현재 피로도, dungeons: 각 던전별 최소 필요 피로도, 소모 피로도
    - 유저가 탐험할 수 있는 최대 던전 수를 return
    
    ## 제한사항
    - k는 1 이상 5,000 이하
    - daungeons의 행: 1 이상 8 이하 -> 던전은 최대 8개
    - 각 행은 ["최소 필요 피로도", "소모 피로도"]
    - 최소 필요 피로도는 항상 소모 피로도보다 크거나 같다
    - 최소 필요 피로도와 소모 피로도는 1 이상 1,000 이하
*/

import java.util.*;

class Solution {
    
    static int answer = 0;
    static int[] selected;
    static int[] used;
    
    static void rec_func(int depth, int len, int k, int[][] dungeons) {
        if (depth == len) {
            int cur = k;
            int cnt = 0;
            
            for (int idx : selected) {
                if (cur >= dungeons[idx][0] && cur >= dungeons[idx][1]) {
                    cnt++;
                    
                    cur -= dungeons[idx][1];
                }
            }
            answer = Math.max(answer, cnt);       
        } else {
            for (int i = 0; i < len; i++) {
                if (used[i] == 1) {
                    continue;
                }
                
                selected[depth] = i;
                used[i] = 1;
                
                rec_func(depth + 1, len, k, dungeons);
                
                selected[depth] = 0;
                used[i] = 0;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        int len = dungeons.length;
        selected = new int[len];
        used = new int[len];
        
        rec_func(0, len, k, dungeons);
        
        return answer;
    }
}