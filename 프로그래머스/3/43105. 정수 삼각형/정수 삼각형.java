/*
    - 거쳐간 숫자의 합이 가장 큰 경우를 찾기
    - 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 or 왼쪽 으로만 이동 가능
    - 최악의 경우 5 * 10^6 (int) 범위
    - 가짜문제: dy[i][j]: i번째 칸에서 j번째 숫자를 골랐을 때 점수
    - 진짜문제: max(dy[끝][모두])
    - 점화식:
        - 1번째 칸인 경우: dy[i][0] = dy[i - 1][0] + triangle[i][0]
        - 마지막 칸인 경우: dy[i][last] = dy[i - 1][이전칸의 last] + triangle[i][last];
        - 이외 칸인 경우: dy[i][j] = max(dy[i - 1][j - 1], dy[i - 1][j]) + triangle[i][j]
*/

import java.util.*;

class Solution {
    
    static int[][] dy;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int len = triangle.length;
        dy = new int[len][len];
        
        dy[0][0] = triangle[0][0];
        
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dy[i][j] += dy[i - 1][j];
                } else if (j == triangle[i].length - 1) {
                    dy[i][j] += dy[i - 1][j - 1];
                } else {
                    dy[i][j] += Math.max(dy[i - 1][j - 1], dy[i - 1][j]);
                }
                
                dy[i][j] += triangle[i][j];
            }
        }
        
        for (int i = 0; i < len; i++) {
            answer = Math.max(answer, dy[len - 1][i]);
        }
        
        return answer;
    }
}