import java.util.*;

class Solution {
    static Queue<Integer>[] adj;
    static int answer = 0;
    static int[][] visit;
    static int size;
    
    public int solution(int[][] points, int[][] routes) {
        // 격자형 배열, n개의 포인트, m개의 경로, 첫 정점에서 할당된 정점을 순서대로 방문
        // 최단 경로 이용, r 좌표가 변하는 이동을 c좌표가 변하는 이동보다 먼저 진행
        // 같은 좌표에 로봇이 2대 이상 모이면 위험 상황 -> bfs?
        size = routes.length; // 로봇의 개수
        adj = new Queue[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new LinkedList<>();
        }
        find(points, routes);
        pro();
        return answer;
    }
    
    void find(int[][] points, int[][] routes) {
        for(int i = 0; i < size; i++) {
            int start = routes[i][0] - 1;
            int sx = points[start][0];
            int sy = points[start][1];
            
            adj[i].offer(sx);
            adj[i].offer(sy);
            
            for(int j = 1; j < routes[0].length; j++){
                int end = routes[i][j] - 1;
                int ex = points[end][0];
                int ey = points[end][1];
            
                while (sx != ex) {
                    if(sx < ex) {
                        sx++;
                    } else {
                        sx--;
                    }
                    adj[i].offer(sx);
                    adj[i].offer(sy);
                }
            
                while (sy != ey) {
                    if (sy < ey) {
                        sy++;
                    } else {
                        sy--;
                    }
                    adj[i].offer(sx);
                    adj[i].offer(sy);
                }
            }
        }    
    }
    
    void pro() {
        int end = 0;
        while(end != size) {
            visit = new int[101][101];
            end = 0; // 나간 로봇수 초기화
            for (int i = 0; i < size; i++) {
                if (adj[i].isEmpty()) {
                    end++; 
                    continue;
                }
                int curX = adj[i].poll();
                int curY = adj[i].poll();
                visit[curX][curY]++;
            }
            answer += check();
        }
    }
    
    int check() {
        int cnt = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (visit[i][j] > 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
