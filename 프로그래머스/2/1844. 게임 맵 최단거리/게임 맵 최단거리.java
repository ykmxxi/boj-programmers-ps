/*
    - 5x5 크기의 격자 행렬, 벽은 지나갈 수 없음
    - 동서남북 4방향 한 칸씩 이동해 (1, 1) -> (n, m) 지나가야 하는 칸의 개수, 이동 불가능 -1
    - 1 <= n, m <= 100 (n != m, (n == 1 && m == 1) 없음)
    - 0(벽), 1(길)
*/
import java.util.*;

class Solution {
    
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] maps) {
        int answer = 0;
        int[][] dist = new int[maps.length][maps[0].length];
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        q.offer(0);
        dist[0][0] = 1;
        
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                
                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length) { // 범위 이탈
                    continue;
                }
                if (dist[nx][ny] != 0 || maps[nx][ny] == 0) { // 이미 방문한 점 or 벽
                    continue;
                }
                dist[nx][ny] = dist[x][y] + 1;
                if (nx == maps.length - 1 && ny == maps[0].length - 1) {
                    return dist[nx][ny];
                }
                q.offer(nx);
                q.offer(ny);
            }
        }
        
        return -1;
    }
    
}
