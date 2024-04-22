/*
    - 세 개의 기둥 존재하고 두 가지 조건을 만족시키면서 한 기둥에 꽂힌 원판들을 순서 그대로 다른 기둥으로 옮기기
    1. 한 번에 하나의 원판만 옮길 수 있다
    2. 큰 원판이 작은 원판 위에 있어서는 안된다
*/

import java.util.*;

class Solution {
    
    List<int[]> ans;
    
    public int[][] solution(int n) {
        ans = new ArrayList<>();
        
        move(n, 1, 2, 3);
        
        return ans.stream()
            .toArray(int[][]::new);
    }
    
    void move(int cnt, int start, int mid, int end) {
        if (cnt == 0) {
            return;
        }
        
        move(cnt - 1, start, end, mid);
        ans.add(new int[]{start, end});
        move(cnt - 1, mid, start, end);
    }
    
}
