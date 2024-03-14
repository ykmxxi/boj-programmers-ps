/*
    - x -> y, 사용 가능한 연산: (x + n), (2 * x), (x * 3)
    - x를 y로 변환하기 위해 필요한 최소 연산 횟수 구하기, 없다면 -1
*/

import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[y + 1];
        
        q.offer(x);
        dist[x] = 0;
        
        // bfs로 풀기
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            if (cur == y) {
                answer = dist[cur];
                break;
            }
            
            if (cur + n <= y && dist[cur + n] == 0) {
                q.offer(cur + n);
                dist[cur + n] = dist[cur] + 1;
            }
            
            if (2 * cur <= y && dist[2 * cur] == 0) {
                q.offer(2 * cur);
                dist[2 * cur] = dist[cur] + 1;
            }
            
            if (3 * cur <= y && dist[3 * cur] == 0) {
                q.offer(3 * cur);
                dist[3 * cur] = dist[cur] + 1;
            }
        }
        
        return answer;
    }
    
}
