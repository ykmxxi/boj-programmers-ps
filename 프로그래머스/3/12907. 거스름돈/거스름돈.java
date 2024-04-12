/*
    - 거스름돈을 n 원을 줄 때 방법의 경우의 수 구하기
    - ex: 5원을 1, 2, 5원으로 거슬러 주는 방법: (5, 0, 0), (3, 1, 0), (1, 2, 0), (0, 0, 1)
    - DP 문제: dy[사용한 화폐 가지수][거슬러줄 돈]
        - ex: dy[0][5]: 5원을 거슬러 줄 때 1원만 사용한 경우, dy[1][5]: 5원을 거슬러줄 때 1 or 2원을 사용한 경우
            dy[2][5]: 5원을 거슬러 줄 때 1 or 2 or 5원 모두 사용한 경우
*/

import java.util.*;

class Solution {
    
    static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        Arrays.sort(money);
        
        int[][] dy = new int[money.length + 1][n + 1];
        for (int i = 1; i <= money.length; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) { // 초기값 설정
                    dy[i][j] = 1;
                } else {
                    if (j < money[i - 1]) { // 현재 거슬러줄 돈이 화폐 단위보다 작으면
                        dy[i][j] = dy[i - 1][j] % MOD; // 경우의 수 추가가 없음
                    } else { // 현재 거슬러줄 돈이 화폐 단위보다 크거나 같으면: 경우의 수 추가 발생
                        dy[i][j] = (dy[i - 1][j] + dy[i][j - money[i - 1]]) % MOD; // 현재 화폐 단위를 추가하는 경우의 수를 더함
                    }
                }
            }
        }
        
        
        return dy[money.length][n];
    }
    
}
