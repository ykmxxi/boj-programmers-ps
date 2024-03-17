/*
    - N x N 정사각형 격자 배열
    - 0: 빈 칸, 1: 벽
    - (0, 0) -> (N - 1, N - 1) 까지 경주로 건설, 경주로는 상하좌우 인접한 빈 칸을 연결해 건설
    - 직선도로 건설 비용: 100원, 코너 건설 비용: 500원
    - 경주로 최소 비용을 계산
*/

import java.util.*;

class Solution {
    
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    
    boolean[][][] visit;
    
    int bfs(int[][] board) {
        int min = Integer.MAX_VALUE;
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0, 0, -1, 0));
        
        while (!q.isEmpty()) {
            Info info = q.poll();
            int x = info.x;
            int y = info.y;
            
            if (x == board.length - 1 && y == board.length - 1) {
                min = Math.min(min, board[x][y]);
            }
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board.length) {
                    continue;
                }
                if (board[nx][ny] == 1) {
                    continue;
                }
                
                int nextCost = info.cost;
                if (info.d == -1 || info.d == k) {
                    nextCost += 100;
                } else {
                    nextCost += 600;
                }
                
                if (!visit[k][nx][ny] || board[nx][ny] >= nextCost) {
                    visit[k][nx][ny] = true;
                    q.offer(new Info(nx, ny, k, nextCost));
                    board[nx][ny] = nextCost;
                }
            }
        }
        
        return min;
    }
    
    public int solution(int[][] board) {
        visit = new boolean[4][board.length][board.length];
        
        return bfs(board);
    }
    
    static class Info {
        int x;
        int y;
        int d;
        int cost;
        
        Info(int x, int y, int d, int cost) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cost = cost;
        }
    }
    
}