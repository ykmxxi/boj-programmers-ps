/*
    - 지나다니는 길 'O', 장애물 'X'
*/

import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        Map<String, int[]> dir = new HashMap<>();
        dir.put("N", new int[]{-1, 0});
        dir.put("S", new int[]{1, 0});
        dir.put("E", new int[]{0, 1});
        dir.put("W", new int[]{0, -1});
        
        int x = 0, y = 0;
        
        Loop1:
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    x = i;
                    y = j;
                    break Loop1;
                }
            }
        }
        
        for (String s : routes) {
            String[] token = s.split(" ");
            
            int[] a = dir.get(token[0]);
            int n = Integer.parseInt(token[1]);
            
            boolean can = true;
            
            for (int i = 1; i <= n; i++) {
                int nx = x + a[0] * i;
                int ny = y + a[1] * i;
                
                if (nx < 0 || ny < 0 || nx >= park.length || ny >= park[0].length()) {
                    can = false;
                    break;
                }
                if (park[nx].charAt(ny) == 'X') {
                    can = false;
                    break;
                }
            }
            
            if (can) {
                x += a[0] * n;
                y += a[1] * n;
            }
        }
        
        return new int[]{x, y};
    }
}