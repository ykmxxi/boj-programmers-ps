/*
    - 1, 0 으로 채워진 격자형 배열에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return
*/

import java.util.*;

class Solution {
    
    public int solution(int[][] board) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;

        int[][] dy = new int[n][m];

        for (int i = 0; i < n; i++) {
            dy[i][0] = board[i][0];
        }

        for (int j = 0; j < m; j++) {
            dy[0][j] = board[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == 1) {
                    dy[i][j] = Math.min(Math.min(dy[i - 1][j - 1], dy[i - 1][j]), dy[i][j - 1]) + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, dy[i][j]);
            }
        }

        return (int)Math.pow(answer, 2);
    }
    
}
