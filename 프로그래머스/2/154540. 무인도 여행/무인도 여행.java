/*
    - X: 바다, 1 ~ 9 숫자는 무인도
    - 상하좌우로 연결되는 땅들은 하나의 무인도로 취급
    - 각 칸에 적힌 숫자는 식량으로 하나의 무인도로 취급되는 곳의 모든 숫자를 합친 것이 최대 머무를수 있는 날자
    - 각 섬에서 최대 며칠씩 머물 수 있는지 구하기
    - 지낼 수 있는 섬이 없으면 -1
*/

import java.util.*;

class Solution {
    
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    int bfs(int startX, int startY, String[] maps, boolean[][] visit) {
        int sum = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(startX);
        q.offer(startY);
        visit[startX][startY] = true;
        sum += maps[startX].charAt(startY) - '0';
        
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            
            for (int k = 0; k < 4; k++) {
                int nx = dir[k][0] + x;
                int ny = dir[k][1] + y;
                
                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length()) {
                    continue;
                }
                if (visit[nx][ny] || maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                q.offer(nx);
                q.offer(ny);
                visit[nx][ny] = true;
                sum += maps[nx].charAt(ny) - '0';
            }
        }
        
        return sum;
    }
    
    public int[] solution(String[] maps) {
        List<Integer> ans = new ArrayList<>();
        
        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'X' || visit[i][j]) {
                    continue;
                }
                ans.add(bfs(i, j, maps, visit));
            }
        }
        
        if (ans.isEmpty()) {
            ans.add(-1);
        }
        Collections.sort(ans);
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
}
