/*
    - n x m 격자형 배열, 석유는 여러 덩어리로
*/
import java.util.*;

class Solution {
    
    static int n, m;
    static boolean[][] visit;
    static int[] cnt;
    static int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    
    static void bfs(int x, int y, int[][] land) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        visit[x][y] = true;
        int size = 1;
        q.offer(x);
        q.offer(y);
        set.add(y);
        
        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (land[nx][ny] == 0) {
                    continue;
                }
                if (visit[nx][ny]) {
                    continue;
                }
                
                q.offer(nx);
                q.offer(ny);
                visit[nx][ny] = true;
                size++;
                set.add(ny);
            }
            
        }
        
        for (int c : set) {
            cnt[c] += size;
        }
        
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        
        cnt = new int[m];
        visit = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] || land[i][j] == 0) {
                    continue;
                }
                bfs(i, j, land);
            }
        }
        
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, cnt[i]);
        }
        
        return answer;
    }
}