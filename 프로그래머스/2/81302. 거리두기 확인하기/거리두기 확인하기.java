/*
    - 대기실 5개, 각각 5x5 크기
    - 응시자들 끼리는 맨해튼 거리가 2이하로 앉기 X, 단 응시자가 앉아있는 자리 사이 파티션이 있으면 허용
    - 맨해튼 거리: 두 정점사이의 맨해튼 거리 = |r1 - r2| + |c1 - c2|
    - 각 대기실별로 거리두기를 지키고 있으면 1, 한 명이라도 지키지 않으면 0
        - P(응시자), O(빈 테이블), X(파티션)
        - 같은 직선상에 있는 경우 사이에 파티션이 있어야 함
        - 대각선에 있을때는 그 사이 나머지 2칸이 파티션이 있어야 함
*/

import java.util.*;

class Solution {
    
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    boolean violation(int sx, int sy, String[] place) {
        Queue<Integer> q = new LinkedList<>();
        int[][] dist = new int[5][5];
        q.offer(sx);
        q.offer(sy);
        
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                    continue;
                }
                if (nx == sx && ny == sy) {
                    continue;
                }
                if (dist[nx][ny] != 0) {
                    continue;
                }
                
                if (place[nx].charAt(ny) == 'P') {
                    if (dist[x][y] == 0) { // (nx, ny)와 1칸 떨어져 있는 경우
                        return true; // 거리두기 위반
                    }
                    if (dist[x][y] == 1) { // (nx, ny)와 2칸 떨어져 있는 경우
                        if (nx == sx && ny != sy) { // 같은 행인 경우
                            if (ny > sy && place[nx].charAt(ny - 1) != 'X') {
                                return true;
                            }
                            if (ny < sy && place[nx].charAt(ny + 1) != 'X') {
                                return true;
                            }
                        }
                        if (nx != sx && ny == sy) { // 같은 열인 경우
                            if (nx > sx && place[nx - 1].charAt(ny) != 'X')  {
                                return true;
                            }
                            if (nx < sx && place[nx + 1].charAt(ny) != 'X') {
                                return true;
                            }
                        }
                        if (nx != sx && ny != sy) { // 대각선인 경우
                            // 오른쪽 위 대각선
                            if (nx < sx && ny > sy) {
                                if (place[sx - 1].charAt(sy) != 'X' || place[sx].charAt(sy + 1) != 'X') {
                                    return true;
                                }
                            }
                            // 오른쪽 아래 대각선
                            if (nx > sx && ny > sy) {
                                if (place[sx + 1].charAt(sy) != 'X' || place[sx].charAt(sy + 1) != 'X') {
                                    return true;
                                }
                            }
                            
                            // 왼쪽 아래 대각선
                            if (nx > sx && ny < sy) {
                                if (place[sx + 1].charAt(sy) != 'X' || place[sx].charAt(sy - 1) != 'X') {
                                    return true;
                                }
                            }
                            
                            // 왼쪽 위 대각선
                            if (nx < sx && ny < sy) {
                                if (place[sx - 1].charAt(sy) != 'X' || place[sx].charAt(sy - 1) != 'X') {
                                    return true;
                                }
                            }
                            
                        }
                    }
                }
                
                if (dist[x][y] + 1 < 2) {
                    q.offer(nx);
                    q.offer(ny);
                    dist[nx][ny] = dist[x][y] + 1;
                }
            }
        }
        
        return false; // 거리두기 위반을 하지 않음
        
    }
    
    boolean check(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (violation(i, j, place)) { // 거리두기 위반
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int i = 0; i < places.length; i++) {
            if (check(places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
}
