/*
    - n * m 격자형 배열, 물에 잠기지 않은 지역을 통해 (1, 1) -> (n, m) 까지 이동
    - 오른쪽과 아래쪽으로만 움직임
    - 최단 경로의 개수를 구하기
    - 가짜문제: dy[i][j] = (i, j) 까지 올 수 있는 최단 경로의 개수
    - 진짜문제: dy[n][m] = (n, m) 까지 올 수 있는 최단 경로의 개수, dy[n - 1][m] + dy[n][m - 1]
    - 점화식
        - 1행일 경우: dy[1][j] = dy[1][j - 1];
        - 1열일 경우: dy[i][1] = dy[i - 1][1];
        - 나머지: dy[i][j] = (dy[i - 1][j] + dy[i][j - 1]) % MOD;
*/


class Solution {
    
    static final int MOD = 1_000_000_007;
    
    boolean check(int[][] puddles, int x, int y) {
        for (int[] puddle : puddles) {
            if (puddle[0] == y && puddle[1] == x) {
                return true;
            }
        }
        return false;
    }
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dy = new int[n + 1][m + 1];
        int[][] dir = {{0, -1}, {-1, 0}}; // 왼쪽, 위쪽
        
        dy[1][1] = 1;
        
        for (int i = 3; i <= m + n; i++) {
            for (int j = 1; j < i; j++) {
                int x = j;
                int y = i - j;
                
                if (x > n || y > m) {
                    continue;
                }
                
                if (check(puddles, x, y)) {
                    continue;
                }
                if (x == 1) { // 1행: 왼쪽만 체크
                    dy[x][y] += dy[x][y - 1];
                } else if (y == 1) { // 1열: 위쪽만 체크
                    dy[x][y] += dy[x - 1][y];
                } else {
                    dy[x][y] += dy[x][y - 1] + dy[x - 1][y];
                    dy[x][y] %= MOD;
                }
            }
        }
        
        return dy[n][m];
    }
}