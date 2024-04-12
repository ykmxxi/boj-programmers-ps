/*
    - 직사각형 격자 베열, 각 칸은 통로 or 벽 -> 벽은 지나갈 수 없고 통로는 이동 가능
    - 통로들 중 한 칸에 빠져나가는 문이 존재, 이 문은 레버를 당겨서만 열 수 있음
    - 먼저 레버가 있는 칸으로 이동 -> 문으로 이동
    - 최대한 빠르게 미로를 빠져나가는 시간 = 레버 최소 시간 + 레버에서 문 까지 최소 시간
*/

import java.util.*;

class Solution {
    
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    int bfs(int sx, int sy, String[] maps, char destination) {
        Queue<Integer> q = new LinkedList<>();
        int[][] dist = new int[maps.length][maps[0].length()];
        q.offer(sx);
        q.offer(sy);
        
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            
            for (int k = 0; k < 4; k++) {
                int nx = dir[k][0] + x;
                int ny = dir[k][1] + y;
                
                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length()) { // 범위를 벗어나거나
                    continue;
                }
                if (nx == sx && ny == sy) { // 시작점이거나
                    continue;
                }
                if (maps[nx].charAt(ny) == 'X') { // 벽이거나
                    continue;
                }
                if (dist[nx][ny] != 0) { // 이미 방문한 점
                    continue;
                }
                
                q.offer(nx);
                q.offer(ny);
                dist[nx][ny] = dist[x][y] + 1;
                if (maps[nx].charAt(ny) == destination) {
                    return dist[nx][ny];
                }
            }
        }
        
        return -1;
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    if (bfs(i, j, maps, 'L') == -1) {
                        return -1;
                    } else {
                        answer += bfs(i, j, maps, 'L');
                    }
                }
                if (maps[i].charAt(j) == 'L') {
                    if (bfs(i, j, maps, 'E') == -1) {
                        return -1;
                    } else {
                        answer += bfs(i, j, maps, 'E');
                    }
                }
            }
        }
        
        return answer;
    }
    
}
